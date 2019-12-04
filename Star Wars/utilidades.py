# -*- coding: UTF-8 -*-
import os
import pygame

def cargar_imagen(archivo, usar_transparencia = False, medida_rect = (0, 0)):
	ruta = os.path.join( "data/", archivo )
	size_x = medida_rect[0]
	size_y = medida_rect[1]
	# Cargamos la imagen
	try:
		imagen = pygame.image.load(ruta)
	except (pygame.error):
		print ("No se pudo cargar la imagen: ", ruta)
		raise (SystemExit)
	
	# Verifacamos se agregamos transparencia
	if usar_transparencia:
		imagen = imagen.convert()
	else:
		imagen = imagen.convert_alpha()
	
	# Escalamos la imagen con el tamaño especificado, si se requiere
	if size_x >= 1 and size_y >= 1:
		imagen = escalar_imagen(imagen, (size_x, size_y))
	else:	# No habrá cambios, conservará su tamaño original
		pass
	
	return imagen
	
def escalar_imagen(imagen, size_required):
	imagen = pygame.transform.scale(imagen, size_required)
	return imagen
	
def cargar_sonido(archivo, nivel_sonido = 1.0):
	class SinSonido:
		def play(self):
			pass
	
	if not pygame.mixer or not pygame.mixer.get_init():
		return SinSonido()
	
	ruta = os.path.join("data/sonidos/", archivo)
	try:
		sound = pygame.mixer.Sound(ruta)
	except (pygame.error):
		print ("No se pudo cargar el sonido: ", ruta)
		raise (SystemExit)
	sound.set_volume(nivel_sonido) # Defino el volumen de la musica
	return sound
