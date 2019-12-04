# -*- coding: UTF-8 -*-

import pygame, random, sys, time
from pygame.locals import *

from utilidades import *

INIT_ENERGY = 100
MEDIDA_NAVE = 50
ANCHO = 800
ALTO = 650
DISPLAYMODE = (ANCHO, ALTO)
TEXTCOLOR = (255, 255, 255)
FPS = 40	# Fotogramas por segundo para que no vaya ni muy rápido ni muy despacio
MAXIMA_CANTIDAD_DROIDES = 15
ASTEROID_MIN_SIZE = 10
ASTEROID_MAX_SIZE = 40
ASTEROID_MIN_SPEED = 1
ASTEROID_MAX_SPEED = 8
RETRASO_FRAMES = 5		# Para simular un retardo en la animación de la explosión
ADDNEW_ASTEROID_RATE = 7
TASA_VELOCIDAD_PLAYER = 6
TASA_ASTEROIDES_ENERGETICOS = 0.4


#------------------------------------------------------------------------------
#   Sprite PLAYER
#------------------------------------------------------------------------------
class Player(pygame.sprite.Sprite):
	def __init__(self):
		pygame.sprite.Sprite.__init__(self)
		self.images = []
		
		# Cargamos todas las imagenes de la animacion
		self.images.append(cargar_imagen('naves/nave_centro.png', False, (MEDIDA_NAVE, MEDIDA_NAVE)))
		self.images.append(cargar_imagen('naves/nave_centro_motor_on.png', False, (MEDIDA_NAVE, MEDIDA_NAVE)))
		self.images.append(cargar_imagen('naves/nave_derecha.png', False, (MEDIDA_NAVE, MEDIDA_NAVE)))
		self.images.append(cargar_imagen('naves/nave_derecha_motor_on.png', False, (MEDIDA_NAVE, MEDIDA_NAVE)))
		self.images.append(cargar_imagen('naves/nave_izquierda.png', False, (MEDIDA_NAVE, MEDIDA_NAVE)))
		self.images.append(cargar_imagen('naves/nave_izquierda_motor_on.png', False, (MEDIDA_NAVE, MEDIDA_NAVE)))
		
		# Cambiará de imagen respecto a la direccion que se desea ir
		self.image = self.images[0]
		# Obtenemos los datos de la 1er imagen. Ya que son todas del mismo largo y ancho
		self.rect = self.image.get_rect()
		self.rect.center = (ANCHO/2, ALTO)
		self.x_speed = 0		# Desplazamiento en X
		self.y_speed = 0		# Desplazamiento en Y
	
	def update(self):
		# Desplazamiento de la nave en las direcciones especificadas
		self.rect.move_ip((self.x_speed, self.y_speed))
		if self.rect.left < 0:	# Preguntamos que no sobrepase los lados
			self.rect.left = 0
		elif self.rect.right > ANCHO:
			self.rect.right = ANCHO
		
		if self.rect.top <= ALTO / 2:	# Preguntamos para que no sobrepase la mitad de la pantalla
			self.rect.top = ALTO / 2
		elif self.rect.bottom >= ALTO:
			self.rect.bottom = ALTO
		
		# Determinamos la dirección del movimiento
		if self.x_speed < 0:	# Izquierda
			if self.y_speed < 0:# Mover adelante a la izquierda
				self.image = self.images[5]
				canal_motor.play(sonido_motores_on, loops=0, maxtime=0, fade_ms=0)
			else:				# Izquierda solamente
				self.image = self.images[4]
				canal_motor.stop()
		elif self.x_speed > 0:	# Derecha
			if self.y_speed < 0:# Mover adelante a la derecha
				self.image = self.images[3]
				canal_motor.play(sonido_motores_on, loops=0, maxtime=0, fade_ms=0)
			else:				# Derecha solamente
				self.image = self.images[2]
				canal_motor.stop()
		else:	# Moviéndose arriba/abajo
			if self.y_speed < 0:# Arriba(acordarse que en el eje Y un número negativo indica ir arriba)
				self.image = self.images[1]
				canal_motor.play(sonido_motores_on, loops=0, maxtime=0, fade_ms=0)
			else:				# Abajo
				self.image = self.images[0]
				canal_motor.stop()
				
#------------------------------------------------------------------------------
#   Sprite ENEMIGO
#------------------------------------------------------------------------------
class Enemigo(pygame.sprite.Sprite):
	def __init__(self):
		pygame.sprite.Sprite.__init__(self)
		self.image = cargar_imagen("enemigos/enemigo.png", False)
		self.rect = self.image.get_rect()
		# Posicionamiento azar
		self.rect.centerx = random.randint(48, 752)
		self.rect.centery = random.randint(70, 230)
		self.x_speed = random.randint(-5, 5)
		self.y_speed = random.randint(-5, 5)
		# En caso que dé 0 le asignamos 1
		if self.x_speed==0:
			self.x_speed=1
		elif self.y_speed==0:
			self.y_speed=1
	
	def update(self):
		self.rect.move_ip((self.x_speed, self.y_speed))
		
		if self.rect.left <= 0 or self.rect.right >= ANCHO:
			self.x_speed = -(self.x_speed)
			
		if self.rect.top <= 0 or self.rect.bottom >= ALTO/2:
			self.y_speed = -(self.y_speed)
		
		# Esto es para evitar que esté disparando todo el tiempo
		disparar = random.randint(1, 80) == 1	# disparar = True si el número aleatorio es 1
		if disparar is True:	# El droide dispara sólo si se le permite
			grupo_laser_enemigo.add(EnemigoLaser(self.rect.midbottom))
			sonido_laser_enemigo.play()
#------------------------------------------------------------------------------
#   Sprite ASTEROIDE
#------------------------------------------------------------------------------
class Asteroide(pygame.sprite.Sprite):
	def __init__(self):
		pygame.sprite.Sprite.__init__(self)
		self.tamano_asteroide = random.randint(ASTEROID_MIN_SIZE, ASTEROID_MAX_SIZE)# Para establecer un tamano variable de asteroides
		self.is_energetico = (random.random() < TASA_ASTEROIDES_ENERGETICOS) # Para determinar al azar si el actual asteroide provee energia
		self.nivel_energia = 0		# Nivel de energía que proveerá el asteroide. 0 es sin energía
		if self.is_energetico:
			self.image = self.seleccionar_imagen("meteoritos/meteorito_energetico.png")
			# Para determinar la cantidad de energía que entrega el asteroide dependiendo su tamaño
			if self.tamano_asteroide <= 10:
				self.nivel_energia = 3
			elif self.tamano_asteroide <= 20:
				self.nivel_energia = 5
			elif self.tamano_asteroide <= 30:
				self.nivel_energia = 7
			elif self.tamano_asteroide > 30:
				self.nivel_energia = 10
		else:	# Asteroide sin energía
			self.image = self.seleccionar_imagen("meteoritos/meteorito.png")
		
		self.rect = pygame.Rect(random.randint(0, ANCHO - self.tamano_asteroide), 0 - self.tamano_asteroide, self.tamano_asteroide, self.tamano_asteroide)
		self.rect.centerx = random.randint(48, ANCHO)
		self.rect.centery = 0
		self.x_speed = random.randint(-(ASTEROID_MAX_SPEED), ASTEROID_MAX_SPEED)
		self.y_speed = random.randint(ASTEROID_MIN_SPEED, ASTEROID_MAX_SPEED)
		
	def update(self):
		self.rect.move_ip((self.x_speed, self.y_speed))
		
		if self.rect.left <= 0 or self.rect.right >= ANCHO or self.rect.bottom >= ALTO:
			self.kill()
	
	def seleccionar_imagen(self, archivo, is_energia_img = False):
		if is_energia_img is True:	# Preguntamos si es para cambiar la imagen por el de energía
			imagen = cargar_imagen(archivo, False, (int(ASTEROID_MAX_SIZE/2), int(ASTEROID_MAX_SIZE/2)))
		else:	# Entonces es una imagen de un meteorito cualquiera
			imagen = cargar_imagen(archivo, False, (self.tamano_asteroide, self.tamano_asteroide))
		
		return imagen
#------------------------------------------------------------------------------
#   Sprite LASER
#------------------------------------------------------------------------------
class PlayerLaser(pygame.sprite.Sprite):
	def __init__(self, pos):
		pygame.sprite.Sprite.__init__(self)
		self.image = cargar_imagen("laser/laser1.png", False)
		self.rect = self.image.get_rect()
		self.rect.center = pos
		
	def update(self):
		if self.rect.bottom <= 0:
			self.kill()
		else:
			self.rect.move_ip((0,-10))

class EnemigoLaser(pygame.sprite.Sprite):
	def __init__( self, pos):
		pygame.sprite.Sprite.__init__(self)
		self.image = cargar_imagen( "laser/laser3.png", False)
		self.rect = self.image.get_rect()
		self.rect.midtop = pos
		
	def update(self):
		if self.rect.bottom >= ALTO:
			self.kill()
		else:
			self.rect.move_ip((0, 6))

#-----------------------------------------------------------------------------------------
class CajaTexto(pygame.sprite.Sprite):
	def __init__(self, texto, fuente, pos_x, pos_y):
		pygame.sprite.Sprite.__init__(self)
		self.fuente = fuente
		self.texto = texto
		self.image = self.fuente.render(self.texto, True, TEXTCOLOR)# (Texto, suavisado de letras, color)
		self.rect = self.image.get_rect()
		self.rect.x = pos_x
		self.rect.y = pos_y
		
	def update(self):
		self.image = self.fuente.render(self.texto, True, TEXTCOLOR)

class Explosion(pygame.sprite.Sprite):
	def __init__(self, objeto_rect, tipo = "explosion"):
		pygame.sprite.Sprite.__init__(self)
		self.indice = 0				# Para cambiar por cada imagen de la lista
		self.tasa_imagen = 0		# Trata de generar numeros grandes y simular un retraso para pasar de una imagen a otra
		self.cantidad_img = 6		# Cantidad de imagenes que contiene la animación
		self.tipo = tipo
		self.imagen_explosion = []	# Anima los sprites que componen la explosion
		
		# Cargamos las imagenes dependiendo del tipo que sea
		for i in range(0, self.cantidad_img):
			nombre_imagen = "explosion/" + tipo + str(i + 1) + ".png"
			self.imagen_explosion.append(cargar_imagen(nombre_imagen, False, objeto_rect.size))
		
		self.image = self.imagen_explosion[self.indice]
		self.rect = self.image.get_rect()
		self.rect.x = objeto_rect.x
		self.rect.y = objeto_rect.y
		
	def update(self):
		# Actualizamos el valor para cambiar de imagen
		self.tasa_imagen += 1
		# Cambiará de imagen cada 4 frames
		if self.tasa_imagen >= RETRASO_FRAMES:
			self.indice += 1
			self.tasa_imagen = 0
			# Mostramos cada imagen de la animación
			if self.indice < len(self.imagen_explosion):
				self.image = self.imagen_explosion[self.indice]
			else:	# En otro caso eliminamos el objeto
				self.kill()
			
# ----------------------------------------------------------------------------
def terminar():
	pygame.quit()
	sys.exit()

def esperar_jugador_presione_tecla():
	while True:
		for event in pygame.event.get():
			if event.type == QUIT:
				terminar()
			if event.type == KEYDOWN:
				if event.key == K_ESCAPE:	# Al pulsar la tecla esc salimos del juego
					terminar()
				return						# Al presionar cualquier tecla salimos y comienza el juego

def dibujar_texto(texto, fuente, surface, x, y):
	text_obj = fuente.render(texto, True, TEXTCOLOR)# Objeto temporal usado sólo para obtener luego el rectángulo(text_obj.get_rect())
	text_rect = text_obj.get_rect()
	text_rect.topleft = (x, y)
	surface.blit(fuente.render(texto, True, TEXTCOLOR), text_rect)

def mostrar_barra_energia(energia):
	# Dibuja una barra de energia vertical coloreada. Tonos rojos para poca energia y verdes para energias altas
	nivel_rojo = 255 - ((energia * 2) + 55)
	if nivel_rojo < 0:
		nivel_rojo = 0
	elif nivel_rojo > 255:
		nivel_rojo = 255
	
	nivel_verde = ((energia * 2) + 55)
	if nivel_verde < 0:
		nivel_verde = 0
	elif nivel_verde > 255:
		nivel_verde = 255
	
	color_rgb = (nivel_rojo, nivel_verde, 0)
	pygame.draw.rect(ventana, color_rgb , (ANCHO - 30, ALTO - 30, 20, -1 * energia))

def mostrar_help():
	ayuda = cargar_imagen('fondos/ayuda.jpg', True, DISPLAYMODE)
	ventana.blit(ayuda, (0, 0))			# Imagen para el tapar el fondo
	pygame.display.update()				# Pintamos toda la pantalla para borrar la imagen anterior
	
	# Mensaje de salida
	dibujar_texto('Pulsa una tecla para volver a jugar', fuente_1, ventana, (ANCHO / 3) , ALTO - 20)
	
	pygame.display.update()
	esperar_jugador_presione_tecla()	# No saldremos del bucle hasta no presionar alguna tecla

def mostrar_about():
	ventana.blit(imagen_fondo, (0, 0))	# Imagen para el tapar el fondo
	pygame.display.update()				# Pintamos toda la pantalla para borrar la imagen anterior
	
	# La imágen
	size_image = 96		# Seleccionamos un tamaño grande
	imagen = cargar_imagen("naves/nave_centro.png", False, (size_image, size_image))
	ventana.blit(imagen, ((ANCHO / 2) - 25, (ALTO / 4)))
	
	# El título
	version = "0.1"
	dibujar_texto("Star Wars " + version, fuente_4, ventana, (ANCHO / 2) - 100, (ALTO / 2))
	
	# Descripción
	dibujar_texto('Juego de tipo "Space shooter" ambientado en la película de Star Wars', fuente_2, ventana, (ANCHO / 3) - 100, (ALTO / 2) + 50)
	
	# Copyright
	dev_year = "2019"			# Año del desarrollo del juego
	current_year = time.strftime("%Y")	# Para determinar el año actual
	if dev_year == current_year:		# Para colocar el año actual o fechas limites(dev_year - current_year)
		intervalo_fecha = dev_year
	else:
		intervalo_fecha = dev_year + " - " + current_year
	dibujar_texto("Copyright © " + intervalo_fecha, fuente_2, ventana, (ANCHO / 3) - 100, (ALTO / 2) + 90)
	
	# Developers
	dibujar_texto("Developers:", fuente_2, ventana, (ANCHO / 3) - 100, (ALTO / 2) + 130)
	dibujar_texto("   ■ Elian Gonzalez:", fuente_3, ventana, (ANCHO / 3) - 100, (ALTO / 2) + 180)
	
	# Mensaje de salida
	dibujar_texto('Pulsa una tecla para volver a jugar', fuente_1, ventana, (ANCHO / 3) - 100, ALTO - 50)
	
	pygame.display.update()
	esperar_jugador_presione_tecla()	# No saldremos del bucle hasta no presionar alguna tecla

def imprime_puntuacion(puntos):
	if OBJETIVO_NIVEL_1<=0:
		imagen_ganaste = cargar_imagen("fondos/ganaste.jpg", True, DISPLAYMODE)
		ventana.blit(imagen_ganaste, (0, 0))
		canal_musica.play(ganaste_sound, loops=0, maxtime=0, fade_ms=0)
	else:
		imagen_perdiste = cargar_imagen("fondos/perdiste.jpg", True, DISPLAYMODE)
		ventana.blit(imagen_perdiste, (0, 0))
		canal_musica.play(perdiste_sound, loops=0, maxtime=0, fade_ms=0)
	
	pygame.display.update()
	dibujar_texto(str(puntos), fuente_5, ventana, (ANCHO / 2)-20, (ALTO / 2)-20)
	pygame.display.update()

def pausa():
	pausado = True
	while pausado:
		for event in pygame.event.get():
			if event.type == QUIT:
				terminar()
			if event.type == KEYDOWN:
				if event.key == K_ESCAPE:	# Al pulsar la tecla esc salimos del juego
					terminar()
				if event.key == K_p:
					pausado = False
		dibujar_texto("PAUSA", fuente_5, ventana, (ANCHO / 2)-50, (ALTO / 2))
		pygame.display.update()

							


#-----------------------------------------------------------------------------------------
# Configuramos pygame
pygame.init()
pygame.mixer.init(frequency=22050, size=-16, channels=8, buffer=4096)
ventana = pygame.display.set_mode(DISPLAYMODE)  # Los valores del tamaño tiene que ser una tupla
pygame.display.set_caption("Star Wars")
set_fps = pygame.time.Clock()
pygame.mouse.set_visible(False)	# Para que el mouse no moleste

# Establecemos el tipo de fuente
fuente_1 = pygame.font.SysFont("Liberation Serif", 24)
fuente_2 = pygame.font.SysFont("Liberation Serif", 20)
fuente_3 = pygame.font.SysFont("Arial", 20)
fuente_4 = pygame.font.SysFont("Times New Roman", 36)
fuente_5 = pygame.font.SysFont("Liberation Serif", 40) #Puntuaciones

# set up sounds (Configuramos los sonidos)
sonido_motores_on = cargar_sonido('motores encendidos.wav', 0.4)
sonido_explosion = cargar_sonido('explosion1.wav', 0.3)
sonido_pickup = cargar_sonido('pickup.wav', 0.3)	# Cuando tocamos la energía
inicio_sound = cargar_sonido('musica_menu.wav', 0.3)
game_over_sound = cargar_sonido('musica_game_over.wav', 1.0)
perdiste_sound = cargar_sonido('perdiste.wav', 1.0)
ganaste_sound = cargar_sonido('ganaste.wav', 1.0)
background_sound = cargar_sonido('musica_juego.wav', 1.0)

enemigo_disparos = ["laser1.wav", "laser2.wav", "laser3.wav", "laser4.wav", "laser5.wav"]	# Seleccionamos al azar cualquiera de estos sonidos para el enemigo
sonido_laser_enemigo = cargar_sonido(random.choice(enemigo_disparos), 0.3)
sonido_laser_player = cargar_sonido('laser_player.wav', 0.3)

explosion_nave_player = cargar_sonido("explosion3.wav", 0.3)
explosion_nave_enemiga = cargar_sonido("explosion1.wav", 0.3)
explosion_asteroide = cargar_sonido("asteroide_destruido.wav", 0.3)
colision_asteroide = cargar_sonido("asteroide_colision.wav", 0.3)

# Configuramos la imagen de fondo
imagen_fondo = cargar_imagen("fondos/background.jpg", True, DISPLAYMODE)
ventana.blit(imagen_fondo, (0, 0))

# Establecemos las imágenes
logo = cargar_imagen('fondos/logo_inicio.png')

# Muestra la pantalla de bienvenida
dibujar_texto("Pulse una tecla para empezar", fuente_1, ventana, (ANCHO / 3) , (ALTO / 3) + 100)
ventana.blit(logo, (150, 100))
pygame.display.update()
canal_motor=pygame.mixer.Channel(4)
canal_musica=pygame.mixer.Channel(3)
canal_musica.play(inicio_sound, loops=-1, maxtime=0, fade_ms=0)
esperar_jugador_presione_tecla()
canal_musica.stop()
#-----------------------------------------------------------------------------------------

top_puntos = 0
while(True):
	# Inicializo el reloj
	if not time.clock():
		tiempo_inicial = time.perf_counter()
	else:
		tiempo_inicial = time.clock()
	
	intervalo_enemigos = 2
	energia = INIT_ENERGY
	OBJETIVO_NIVEL_1=100
	puntos = 0
	contador_asteroides = 0
	
	# ================================
	# Creamos los Sprites y los grupos
	# ================================
	# Configuramos el jugador
	jugador = Player()
	grupo_jugador = pygame.sprite.RenderUpdates(jugador)
	grupo_laser_jugador = pygame.sprite.RenderUpdates()
	
	# Configuramos los enemigos
	grupo_enemigo = pygame.sprite.RenderUpdates()
	# Agregamos 3 enemigos al principio
	grupo_enemigo.add(Enemigo())
	grupo_enemigo.add(Enemigo())
	grupo_enemigo.add(Enemigo())
	grupo_laser_enemigo = pygame.sprite.RenderUpdates()
	
	# Configuramos los asteroides
	grupo_asteroides = pygame.sprite.RenderUpdates()
	grupo_energia = pygame.sprite.RenderUpdates()
	
	# Configuramos las cajas que contendrán texto
	caja_puntos =		CajaTexto("Puntos: {}".format(puntos), fuente_1, 10, 0)
	caja_top_puntos =	CajaTexto("Mejor: {}".format(top_puntos), fuente_1, 10, 40)
	caja_objetivos =	CajaTexto("Objetivo: {}".format(OBJETIVO_NIVEL_1), fuente_1, 10, 80)
	caja_tiempo =		CajaTexto("Tiempo: {0:.2f}".format(tiempo_inicial), fuente_1, ANCHO - 150, 0)
	caja_energia =		CajaTexto("Energia: {}".format(energia), fuente_1, ANCHO - 150, 40)
	caja_informacion =	CajaTexto("Presione: ESC-Salir     F1-Ayuda     F2-Acerca de...", fuente_1, 10, ALTO - 40)
	grupo_cajas = pygame.sprite.RenderUpdates(caja_puntos, caja_top_puntos, caja_objetivos, caja_tiempo, caja_energia, caja_informacion)
	
	# Configuramos un objeto para simular la explosion
	grupo_explosion = pygame.sprite.RenderUpdates()
	
	canal_musica.play(background_sound, loops=-1, maxtime=0, fade_ms=0)
	contar_ciclos = 0
	presionar_teclas = True
	while (True):
		# Pintamos el fondo
		ventana.blit(imagen_fondo, (0, 0))
		
		# En caso de que el jugador pierda no se le permite utilizar teclas
		if presionar_teclas is True:
			for event in pygame.event.get():
				if event.type == QUIT:
					terminar()
				elif event.type == KEYDOWN: # Preguntamos si se ha presionado una tecla
					if event.key == K_F1:
						mostrar_help()
					if event.key == K_F2:
						mostrar_about()
					if event.key == K_p:
						pausa()
					if event.key == K_ESCAPE:
						terminar()
					if event.key == K_SPACE:
						sonido_laser_player.play()
						grupo_laser_jugador.add(PlayerLaser(jugador.rect.midtop))
				elif event.type == KEYUP:  # Preguntamos si se ha dejado de presionar cualquier tecla
					jugador.x_speed, jugador.y_speed = 0, 0
					
			# Sirve para mover a la nave del jugador por el espacio
			# Con las teclas de navegación y letras.
			teclas_pulsadas = pygame.key.get_pressed()
			if teclas_pulsadas[K_a] or teclas_pulsadas[K_LEFT]:
				jugador.x_speed = -(TASA_VELOCIDAD_PLAYER)
			if teclas_pulsadas[K_d] or teclas_pulsadas[K_RIGHT]:
				jugador.x_speed = TASA_VELOCIDAD_PLAYER
			if teclas_pulsadas[K_w] or teclas_pulsadas[K_UP]:
				jugador.y_speed = -(TASA_VELOCIDAD_PLAYER)
			if teclas_pulsadas[K_s] or teclas_pulsadas[K_DOWN]:
				jugador.y_speed = TASA_VELOCIDAD_PLAYER
		elif presionar_teclas is False:		# Entramos recién en el bloque luego de que la energía se agote
			# 6 es la cantidad de imagenes de la animación * el RETRASO + 20 ciclos más para tener un momento de pausa
			total_ciclos = (RETRASO_FRAMES * 6) + 20
			if contar_ciclos == total_ciclos:
				break	# SALIMOS DEL CICLO WHILE
				
			contar_ciclos = contar_ciclos + 1	# Aumentamos el contador
		
		# Pone mas asteroides en la parte alta de la pantalla, si son necesarios       
		contador_asteroides += 1
		if contador_asteroides == ADDNEW_ASTEROID_RATE:
			contador_asteroides = 0
			grupo_asteroides.add(Asteroide())
		
		# Crear los droides enemigos
		# Sirve para agregar droides cada vez que su valor sea mayor o igual a 500
		# Luego se resetea. Es para no estar agregando droides de manera abusiva.
		intervalo_enemigos += 2
		if (len(grupo_enemigo)) <= MAXIMA_CANTIDAD_DROIDES and intervalo_enemigos >= 450:	# Limitamos la cantidad maxima de droides que se crearan
			grupo_enemigo.add(Enemigo())
			intervalo_enemigos = 0
		elif (len(grupo_enemigo)) <= MAXIMA_CANTIDAD_DROIDES and intervalo_enemigos == 210:	# Limitamos la cantidad maxima de droides que se crearan
			grupo_enemigo.add(Enemigo())
			grupo_enemigo.add(Enemigo())
		elif (len(grupo_enemigo)) <= MAXIMA_CANTIDAD_DROIDES and intervalo_enemigos == 50:	# Limitamos la cantidad maxima de droides que se crearan
			grupo_enemigo.add(Enemigo())
			grupo_enemigo.add(Enemigo())
			grupo_enemigo.add(Enemigo())
		
		# Para contar el tiempo
		if not time.clock():
			tiempo_actual = time.perf_counter()
		else:
			tiempo_actual = time.clock()
		tiempo = tiempo_actual - tiempo_inicial
		tiempo = tiempo * 2
		
		# Muerte del personaje. Controlamos que se realice 1 vez
		if energia <= 0 and presionar_teclas is True:
			# Verificamos si supera el mejor puntaje
			if puntos > top_puntos:
				top_puntos = puntos
			explosion_nave_player.play()
			presionar_teclas = False		# Para deshabilitar el ingreso de pulsaciones
			grupo_explosion.add(Explosion(jugador.rect))
			jugador.kill()					# Matamos al personaje
			contar_ciclos = 0
		#Gana el personaje
		elif OBJETIVO_NIVEL_1<=0:
			# Verificamos si supera el mejor puntaje
			if puntos > top_puntos:
				top_puntos = puntos
			presionar_teclas = False		# Para deshabilitar el ingreso de pulsaciones
			jugador.kill()
			contar_ciclos = 0
			break
#-----------------------------------------------------------------------------------------
		# COLISIONES DE LOS SPRITES
		# =========================
		# Daño ocasionado por los láseres de los enemigos al jugador
		for player in pygame.sprite.groupcollide(grupo_jugador, grupo_laser_enemigo, False, True):
			energia = energia - 5
		# Para que el láser destruya la nave enemiga
		for enemigo in pygame.sprite.groupcollide(grupo_enemigo, grupo_laser_jugador, True, True):
			puntos += 15
			grupo_explosion.add(Explosion(enemigo.rect, "explosion"))
			explosion_nave_enemiga.play()
			OBJETIVO_NIVEL_1-=1;
		# Para que el láser destruya los asteroides
		for asteroide in pygame.sprite.groupcollide(grupo_asteroides, grupo_laser_jugador, False,True):
			puntos += 5
			grupo_explosion.add(Explosion(asteroide.rect, "smoke"))	# En vez de una explosion el asteroide desaparece en una nube de humo
			explosion_asteroide.play()
			# Preguntamos si es un asteroide que provee energía para cambiar la imagen
			if (asteroide.is_energetico is True):
				asteroide.image = asteroide.seleccionar_imagen("energia/energy.png", True)
				asteroide.x_speed = 0 # Para que caiga verticalmente
				asteroide.y_speed = 2 # Para disminuir su velocidad
				grupo_energia.add(asteroide)
				grupo_asteroides.remove(asteroide)
			else:	# Destruimos directamente al asteroide
				asteroide.kill()
		# Cuando un asteroide choca a la nave
		for asteroide in pygame.sprite.groupcollide(grupo_asteroides, grupo_jugador, True, False):
			energia = energia - 7			# Disminuye la energía que posee la nave
			grupo_explosion.add(Explosion(asteroide.rect, "smoke"))
			colision_asteroide.play()
		# Cuando un droide choca a la nave
		for enemigo in pygame.sprite.groupcollide(grupo_enemigo, grupo_jugador, True, False):
			energia = energia - 7		# Disminuyo la energía que posee la nave
			grupo_explosion.add(Explosion(enemigo.rect, "explosion"))
			explosion_nave_enemiga.play()
		# Cuando tocamos la energía
		for e in pygame.sprite.groupcollide(grupo_energia, grupo_jugador, True, False):
			if energia < 100:
				energia = energia + e.nivel_energia		# Proporciona energía dependiendo del asteroide
				# Trataremos de que no pase de los límites
				if energia >= 100:
					energia = 100
				
			sonido_pickup.play()
#-----------------------------------------------------------------------------------------
		# Actualizamos todos los grupos
		# =============================
		grupo_jugador.update()
		grupo_laser_jugador.update()
		grupo_enemigo.update()
		grupo_laser_enemigo.update()
		grupo_asteroides.update()
		grupo_energia.update()
		grupo_cajas.update()
		grupo_explosion.update()
		
		# REDIBUJAMOS LOS SPRITES
		# =======================
		grupo_jugador.clear(ventana, imagen_fondo)
		grupo_laser_jugador.clear(ventana, imagen_fondo)
		grupo_enemigo.clear(ventana, imagen_fondo)
		grupo_laser_enemigo.clear(ventana, imagen_fondo)
		grupo_asteroides.clear(ventana, imagen_fondo)
		grupo_energia.clear(ventana, imagen_fondo)
		grupo_cajas.clear(ventana, imagen_fondo)
		grupo_explosion.clear(ventana, imagen_fondo)
		
		grupo_jugador.draw(ventana)
		grupo_laser_jugador.draw(ventana)
		grupo_enemigo.draw(ventana)
		grupo_laser_enemigo.draw(ventana)
		grupo_asteroides.draw(ventana)
		grupo_energia.draw(ventana)
		grupo_cajas.draw(ventana)
		grupo_explosion.draw(ventana)
		
		# Pone las puntuaciones y tiempos
		caja_puntos.texto = "Puntos: %d " % (puntos)
		caja_top_puntos.texto = "Mejor: %d " % (top_puntos)
		caja_objetivos.texto = "Objetivo: %d" % (OBJETIVO_NIVEL_1)
		caja_tiempo.texto = "Tiempo: %.2f" % (tiempo)	# Para que lo muestre con 2 decimales
		caja_energia.texto = "Energia: {0}%".format(int(energia))
		caja_informacion.texto = "Presione: ESC-Salir     F1-Ayuda     F2-Acerca de"
		
		# Mostramos la barra de energía
		mostrar_barra_energia(energia)
		
		pygame.display.update()
		set_fps.tick(FPS)
	
	# Para el juego y muestra la pantalla de Game Over
	dibujar_texto('GAME OVER', fuente_1, ventana, (ANCHO / 2) - 50, (ALTO / 3))
	pygame.display.update()
	intervalo_pausa = 2000
	pygame.time.delay(intervalo_pausa)
	canal_musica.stop()

	# Imprimimos el puntaje
	imprime_puntuacion(puntos)
	intervalo_pausa = 4000
	pygame.time.delay(intervalo_pausa)
	esperar_jugador_presione_tecla()
	canal_musica.stop()
