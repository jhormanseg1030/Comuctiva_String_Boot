-- ============================================
-- Script: Agregar ID_Usuario (Vendedor) a Producto
-- Fecha: 2025-11-03
-- Propósito: Identificar qué usuario vendió cada producto
-- ============================================

USE comuctiva;

-- 1. Agregar columna ID_Usuario a la tabla producto
ALTER TABLE producto 
ADD COLUMN IF NOT EXISTS ID_Usuario INT(10) NULL AFTER categoria;

-- 2. Agregar Foreign Key constraint
ALTER TABLE producto
ADD CONSTRAINT FK_Producto_Usuario 
FOREIGN KEY (ID_Usuario) REFERENCES usuario(ID_Usuario)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- 3. Verificar que se agregó correctamente
DESCRIBE producto;

-- 4. (OPCIONAL) Actualizar productos existentes asignándoles un vendedor por defecto
-- Descomenta la siguiente línea si quieres asignar todos los productos existentes a un usuario específico
-- UPDATE producto SET ID_Usuario = 1 WHERE ID_Usuario IS NULL;

SELECT 'Columna ID_Usuario agregada exitosamente a la tabla producto' AS Resultado;
