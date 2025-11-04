-- ============================================
-- Script: Datos de Prueba para Sistema Ventas/Compras
-- Fecha: 2025-11-03
-- Propósito: Asignar vendedores a productos para probar el sistema
-- ============================================

USE comuctiva;

-- 1. Verificar usuarios existentes
SELECT ID_Usuario, NomUsu, apell1, correo
FROM usuario 
LIMIT 5;

-- 2. Asignar productos a vendedores (ajusta los IDs según tus usuarios)
-- Asumiendo que tienes usuarios con ID 1, 2, 3

-- Asignar Frutas al Usuario 1
UPDATE producto 
SET ID_Usuario = 1
WHERE categoria = 'Frutas' 
AND ID_Usuario IS NULL
LIMIT 10;

-- Asignar Verduras al Usuario 2 (si existe)
UPDATE producto 
SET ID_Usuario = 2 
WHERE categoria = 'Verduras' 
AND ID_Usuario IS NULL
LIMIT 10;

-- Asignar Lácteos al Usuario 1
UPDATE producto 
SET ID_Usuario = 1
WHERE categoria = 'Lácteos' 
AND ID_Usuario IS NULL
LIMIT 10;

-- Asignar Granos al Usuario 1
UPDATE producto 
SET ID_Usuario = 1
WHERE categoria = 'Granos' 
AND ID_Usuario IS NULL
LIMIT 10;

-- 3. Verificar que se asignaron correctamente
SELECT 
    p.ID_Producto,
    p.nomprod AS Producto,
    p.categoria AS Categoria,
    p.ID_Usuario AS ID_Vendedor,
    u.NomUsu AS Vendedor
FROM producto p
LEFT JOIN usuario u ON p.ID_Usuario = u.ID_Usuario
WHERE p.ID_Usuario IS NOT NULL
ORDER BY p.categoria, p.nomprod;

-- 4. Ver estadisticas de productos por vendedor
SELECT 
    u.ID_Usuario,
    u.NomUsu AS Vendedor,
    COUNT(p.ID_Producto) AS Total_Productos
FROM usuario u
INNER JOIN producto p ON p.ID_Usuario = u.ID_Usuario
GROUP BY u.ID_Usuario, u.NomUsu;

SELECT 'Productos asignados a vendedores correctamente' AS Resultado;
