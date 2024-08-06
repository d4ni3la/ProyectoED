create database trenes;
use trenes;

create table if not exists registro(
	idRegistro int not null auto_increment,
    idL int not null,
    idE int not null,
	tipo int not null,
    nombre varchar(60),
    primary key(idRegistro)
);

create table if not exists nombresMatriz(
	id int not null auto_increment,
    nombre varchar(60),
    primary key(idRegistro)
);

drop table registro;

-- TIPOS
-- 1 - nodo de cruce
-- 2 - normal

-- SELECT * FROM registro;

/*LINEA 1*/
insert into registro(idL, idE, nombre, tipo) values 
	(1, 1, "Mirador", 2), (1, 2, "Huentitán", 2), (1, 3, "Zoológico", 2), (1, 4, "Independencia Norte", 1),
    (1, 5, "San Patricio", 2), (1, 6, "Igualdad", 2), (1, 7, "Monumental", 2), (1, 8, "Monte Olivette", 2),
    (1, 9, "Circunvalación 2", 2), (1, 10, "Facultad de Medicina", 1), (1, 11, "Juan Álvarez", 2), (1, 12, "Alameda", 2),
    (1, 13, "San Juan de Dios", 1), (1, 14, "Bicentenario", 2), (1, 15, "Niños Héroes", 2), (1, 16, "Agua Azul", 1),
    (1, 17, "Ciprés", 2), (1, 18, "Héroe de Nacozari", 2), (1, 19, "Lázaro Cardenas", 2), (1, 20, "El Dean", 1),
    (1, 21, "Zona Industrial", 2), (1, 22, "López de Legazpi", 2), (1, 23, "Clemente Orozco", 2), (1, 24, "Artes Plásticas", 2),
    (1, 25, "Escultura", 2), (1, 26, "Fray Angélico", 1);

/*LINEA 2*/
insert into registro(idL, idE, nombre, tipo) values 
	(2, 1, "Basílica", 2), (2, 2, "Sanatorio", 2), (2, 3, "Colegio Victoria", 2), (2, 4, "Plaza Patria", 1),
    (2, 5, "Terranova", 2), (2, 6, "Colón", 1), (2, 7, "Lienzo Charro", 2), (2, 8, "Mezquitán", 1),
    (2, 9, "Panteón de Belén", 2), (2, 10, "Procuraduría", 2), (2, 11, "Facultad de Medicina", 1), (2, 12, "Obrero", 2),
    (2, 13, "Talpita", 2), (2, 14, "El Jaraz", 2), (2, 15, "Plutarco Elías Calles", 2), (2, 16, "Haciendas", 2),
    (2, 17, "Oblatos 1", 2), (2, 18, "Bethel", 2);

/*LINEA 3*/
insert into registro(idL, idE, nombre, tipo) values 
	(3, 1, "Periférico Norte", 1), (3, 2, "Dermatológico", 2), (3, 3, "Atemajac", 2), (3, 4, "División del Norte", 1),
    (3, 5, "Ávila Camacho", 2), (3, 6, "Mezquitán", 1), (3, 7, "Refugio", 2), (3, 8, "Juárez", 1),
    (3, 9, "Mexicaltzingo", 2), (3, 10, "Washington", 1), (3, 11, "Santa Filomena", 2), (3, 12, "Unidad Deportiva", 1),
    (3, 13, "Urdaneta", 2), (3, 14, "18 de Marzo", 2), (3, 15, "Isla Raza", 2), (3, 16, "Patria Sur", 2),
    (3, 17, "España", 2), (3, 18, "Tesoro", 2), (3, 19, "Periférico Sur", 1);

/*LINEA 4*/
insert into registro(idL, idE, nombre, tipo) values 
	(4, 1, "Central Sur", 2), (4, 2, "Vallarta", 1), (4, 3, "Jardines de la Paz", 2), (4, 4, "U. Panamericana", 2),
    (4, 5, "Juan Palomar", 2), (4, 6, "Seminario", 1), (4, 7, "Cámara de Comercio", 2), (4, 8, "Minerva", 2),
    (4, 9, "Centro Magno", 2), (4, 10, "Américas", 1), (4, 11, "Chapultepec", 2), (4, 12, "Paraninfo", 2),
    (4, 13, "Juárez", 1), (4, 14, "Plaza Universidad", 2), (4, 15, "San Juan de Dios", 1), (4, 16, "Belisario Domínguez", 2),
    (4, 17, "Oblatos 2", 2), (4, 18, "Cristobal de Oñate", 2), (4, 19, "San Andrés", 2), (4, 20, "San Jacinto", 2),
    (4, 21, "La Aurora", 2), (4, 22, "Tetlán", 2);

/*LINEA 5*/
insert into registro(idL, idE, nombre, tipo) values 
	(5, 1, "San Isidro", 1), (5, 2, "CUCEA", 2), (5, 3, "Parque", 2), (5, 4, "Seatle", 2),
    (5, 5, "Zoquipan", 1), (5, 6, "Country", 2), (5, 7, "Hospital General", 2), (5, 8, "Plaza Patria", 1),
    (5, 9, "Colomos", 2), (5, 10, "Plaza Pabellón", 2), (5, 11, "San Javier", 2), (5, 12, "3 de Marzo", 2),
    (5, 13, "Jardines Universidad", 2), (5, 14, "Ferrocarril", 2), (5, 15, "Seminario", 1), (5, 16, "La Gran Plaza", 2),
    (5, 17, "San Ignacio", 2), (5, 18, "Estampida", 1), (5, 19, "Chapalita", 2), (5, 20, "Abastos", 1),
    (5, 21, "Mandarina", 2), (5, 22, "Ruiseñor", 2), (5, 23, "Unidad Deportiva", 1), (5, 24, "Plaza las Torres", 2),
    (5, 25, "Cristo Rey", 2), (5, 26, "El Dean", 1), (5, 27, "Nogalera", 2), (5, 28, "Álamo", 2),
    (5, 29, "Textiles", 2);

/*LINEA 6*/
insert into registro(idL, idE, nombre, tipo) values 
	(6, 1, "Tabachines", 1), (6, 2, "Centro Cultural", 2), (6, 3, "Zoquipan", 1), (6, 4, "Patria", 2),
    (6, 5, "División del Norte", 1), (6, 6, "Lomas", 2), (6, 7, "Plan de San Luis", 2), (6, 8, "Colón", 1),
    (6, 9, "Jose María Vigil", 2), (6, 10, "Zarco", 2), (6, 11, "Av. México", 2), (6, 12, "Ladrón de Guevara", 2),
    (6, 13, "Américas", 1), (6, 14, "Lafayette", 2), (6, 15, "Chapu", 2), (6, 16, "Monumento", 1),
    (6, 17, "Santa Eduwiges", 2), (6, 18, "Día", 2), (6, 19, "Abastos", 1), (6, 20, "Parque de las Estrellas", 2),
    (6, 21, "Expo", 2), (6, 22, "Plaza del Sol", 2);

/*Linea 7*/
insert into registro(idL, idE, nombre, tipo) values
(7, 1, "Arco del Triunfo", 2),(7, 2, "Belenes", 1),(7, 3, "Mercado del Mar", 2),(7, 4, "Zapopan Centro", 2),(7, 5, "Plaza Patria", 1),
(7, 6, "Circunvalación", 2),(7, 7, "División del Norte", 1),(7, 8, "Normal", 2),(7, 9, "Santuario", 2),(7, 10, "San Juan de Dios", 1),
(7, 11, "Independencia Sur", 2),(7, 12, "Plaza de la Bandera", 2),(7, 13, "CUCEI", 1),(7, 14, "Plaza Revolución", 2),(7, 15, "Río Nilo", 2),
(7, 16, "Tlaquepaque", 2),(7, 17, "Nodo Revolución", 2),(7, 18, "Central Camionera", 2);

/*Linea 8*/
Insert into registro(idL, idE, nombre, tipo) values
(8, 1, "Parque Metropolitano", 1),(8, 2, "La Estancia", 2),(8, 3, "Guadalupe", 2),(8, 4, "UNIVA", 2),(8, 5, "Juan Diego", 2),
(8, 6, "Estampida", 1),(8, 7, "Inglaterra", 2),(8, 8, "Embajada", 2),(8, 9, "Monumento", 1),(8, 10, "Argentina", 2),
(8, 11, "Francia", 2),(8, 12, "Madrid", 2),(8, 13, "Washington", 1),(8, 14, "Carteros", 2),(8, 15, "Agua Azul", 1),
(8, 16, "Gonzáles Gallo", 2),(8, 17, "CUCEI", 1),(8, 18, "Medrano", 2), (8, 19, "San Rafael", 2),(8, 20, "Poetas", 2);

/*Linea 9*/
Insert into registro(idL, idE, nombre, tipo) values
(9, 1, "Barranca de Huentitán", 2),(9, 2, "Zoológico Guadalajara", 2),(9, 3, "Independencia Norte", 1),(9, 4, "Lomas del Paraíso", 2),(9, 5, "Rancho Nuevo", 2),
(9, 6, "La Esperiencia", 2),(9, 7, "El Batán", 2),(9, 8, "Periférico Norte", 1),(9, 9, "La Cantera", 2),(9, 10, "Tabachines", 1),
(9, 11, "Constitución", 2),(9, 12, "San Isidro", 1),(9, 13, "Belenes", 1),(9, 14, "Tuzanía", 2),(9, 15, "Santa Margarita", 2),
(9, 16, "Acueducto", 2),(9, 17, "5 de Mayo", 2),(9, 18, "San Juan de Ocotán", 2),(9, 19, "Vallarta", 1),(9, 20, "Estadio Chivas", 2),
(9, 21, "Ciudad Judicial", 2),(9, 22, "Ciudad Granja", 2),(9, 23, "Parque Metropolitano", 1),(9, 24, "Chapalita Inn", 2),(9, 25, "El Coli", 2),
(9, 26, "Felipe Ruvalcaba", 2),(9, 27, "Miramar", 2),(9, 28, "Mariano Otero", 2),(9, 29, "El Briseño", 2),(9, 30, "Agricola", 2),
(9, 31, "López Mateos", 2),(9, 32, "ITESO", 2),(9, 33, "Terminal de Autobuses", 2),(9, 34, "Periférico Sur", 1),(9, 35, "San Sebastianito", 2),
(9, 36, "8 de Julio", 2),(9, 37, "Toluquilla", 2),(9, 38, "Adolf Horn", 1),(9, 39, "Artesanos", 2),(9, 40, "Las Pintas", 2),
(9, 41, "Chapala", 2);

/*Linea 10*/
Insert into registro(idL, idE, nombre, tipo) values
(10, 1, "Fray Angélico", 1),(10, 2, "Periférico", 2),(10, 3, "Adolf Horn", 1),(10, 4, "Concepción", 2),(10, 5, "Carretera a Tlajomulco", 2),
(10, 6, "Lomas del Sur", 2),(10, 7, "Cortijo", 2),(10, 8, "Escobedo", 2),(10, 9, "Circuito Metropolitano", 2);

-- MATRIZ                             
-- INSERT INTO nombresMatriz(id, nombre) values 
	-- (7,"División del Norte"),(9,"Colón"), (1,"Mezquitán"), (2,"Facultad de Medicina"), (3,"Seminario"), (4,"Américas"), (5,"Juárez"), (6,"San Juan de Dios"), 
   --  ;