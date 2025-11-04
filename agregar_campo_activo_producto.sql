-- Script para agregar campo 'activo' a la tabla Producto
-- Este campo permite activar/desactivar productos

USE comuctiva;

-- Verificar si la columna ya existe antes de agregarla
SET @col_exists = 0;
SELECT COUNT(*) INTO @col_exists 
FROM information_schema.COLUMNS 
WHERE TABLE_SCHEMA = 'comuctiva' 
AND TABLE_NAME = 'producto' 
AND COLUMN_NAME = 'activo';

-- Solo agregar la columna si no existe
SET @sql = IF(@col_exists = 0, 
    'ALTER TABLE producto ADD COLUMN activo TINYINT(1) DEFAULT 1 COMMENT "1=Activo, 0=Inactivo"',
    'SELECT "La columna activo ya existe en la tabla producto" as mensaje'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Actualizar productos existentes para que estén activos por defecto
UPDATE producto SET activo = 1 WHERE activo IS NULL;

-- Mostrar el estado actual de algunos productos
SELECT id_producto, nomprod, estado, activo, 
       CASE 
           WHEN activo = 1 THEN 'ACTIVO' 
           WHEN activo = 0 THEN 'INACTIVO' 
           ELSE 'SIN DEFINIR' 
       END as estado_activacion
FROM producto 
LIMIT 10;

SELECT 'Campo activo agregado exitosamente a la tabla producto' as resultado;
