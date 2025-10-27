-- Script para agregar la columna categoria a la tabla producto
USE co;

-- Agregar columna categoria si no existe
ALTER TABLE producto 
ADD COLUMN IF NOT EXISTS categoria VARCHAR(50);

-- Verificar que se agregó correctamente
DESCRIBE producto;
