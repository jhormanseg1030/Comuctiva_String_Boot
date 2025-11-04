-- Script para asignar productos a vendedores según tipo de producto
USE comuctiva;

-- Asignar Verduras al Usuario 2 (Ana)
UPDATE producto SET ID_Usuario = 2, categoria = 'Verduras' 
WHERE nomprod IN ('Tomate Chonto', 'Cebolla Cabezona', 'Lechuga Crespa', 'Zanahoria');

-- Asignar Tuberculos al Usuario 3 (Pedro)
UPDATE producto SET ID_Usuario = 3, categoria = 'Tuberculos' 
WHERE nomprod IN ('Papa Criolla');

-- Asignar Frutas al Usuario 4 (Laura)
UPDATE producto SET ID_Usuario = 4, categoria = 'Frutas' 
WHERE nomprod IN ('Aguacate Hass');

-- Asignar Granos al Usuario 5 (Juan)
UPDATE producto SET ID_Usuario = 5, categoria = 'Granos' 
WHERE nomprod IN ('Frijol Rojo', 'Arroz Blanco', 'Lenteja', 'Garbanzo', 'Arroz Bulto 50kg');

-- Asignar Lacteos al Usuario 2 (Ana)
UPDATE producto SET ID_Usuario = 2, categoria = 'Lacteos' 
WHERE nomprod IN ('Leche Entera', 'Queso Campesino', 'Yogurt Natural');

-- Verificar resultado
SELECT 
    p.ID_Producto,
    p.nomprod AS Producto,
    p.categoria AS Categoria,
    p.ID_Usuario AS ID_Vendedor,
    u.NomUsu AS Vendedor
FROM producto p
LEFT JOIN usuario u ON p.ID_Usuario = u.ID_Usuario
ORDER BY p.categoria, p.nomprod;

SELECT '✅ Productos asignados correctamente a los vendedores' AS Resultado;
