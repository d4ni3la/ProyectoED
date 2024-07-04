create database metro;
use metro;

create table if not exists registro(
	idRegistro int not null auto_increment,
    idL int not null,
    idE int not null,
    nombre varchar(60),
    primary key(idRegistro)
);

-- drop table registro;

/*LINEA 1*/
insert into registro(idL, idE, nombre) values 
	(1, 1, "Mirador"), (1, 2, "Huentitán"), (1, 3, "Zoológico"), (1, 4, "Independecia Norte"),
    (1, 5, "San Patricio"), (1, 6, "Igualdad"), (1, 7, "Monumental"), (1, 8, "Monte Olivette"),
    (1, 9, "Circunvalación 2"), (1, 10, "Facultad de Medicina"), (1, 11, "Juan Álvarez"), (1, 12, "Alameda"),
    (1, 13, "San Juan de Dios"), (1, 14, "Bicentenario"), (1, 15, "Niños Héroes"), (1, 16, "Agua Azul"),
    (1, 17, "Ciprés"), (1, 18, "Héroe de Nacozari"), (1, 19, "Lázaro Cardenas"), (1, 20, "El Dean"),
    (1, 21, "Zona Industrial"), (1, 22, "López de Legazpi"), (1, 23, "Clemente Orozco"), (1, 24, "Artes Plásticas"),
    (1, 25, "Escultura"), (1, 26, "Fray Angélico");

/*LINEA 2*/
insert into registro(idL, idE, nombre) values 
	(2, 1, "Basílica"), (2, 2, "Sanatorio"), (2, 3, "Colegio Vctoria"), (2, 4, "Plaa Patria"),
    (2, 5, "Terranova"), (2, 6, "Colón"), (2, 7, "Lienzo Charro"), (2, 8, "Mezquitán"),
    (2, 9, "Panteón de Belén"), (2, 10, "Procuraduría"), (2, 11, "Facultad de Medicina"), (2, 12, "Obrero"),
    (2, 13, "Talpita"), (2, 14, "El Jaraz"), (2, 15, "Plutarco Elías Calles"), (2, 16, "Haciendas"),
    (2, 17, "Oblatos 1"), (2, 18, "Bethel");

/*LINEA 3*/
insert into registro(idL, idE, nombre) values 
	(3, 1, "Perférico Norte"), (3, 2, "Dermatológico"), (3, 3, "Atemajac"), (3, 4, "División del Norte"),
    (3, 5, "Ávila Camacho"), (3, 6, "Mezquitán"), (3, 7, "Refugio"), (3, 8, "Juárez"),
    (3, 9, "Mexicaltzingo"), (3, 10, "Washington"), (3, 11, "Santa Filomena"), (3, 12, "Unidad Deportiva"),
    (3, 13, "Urdaneta"), (3, 14, "18 de Marzo"), (3, 15, "Isla Raza"), (3, 16, "Patria Sur"),
    (3, 17, "España"), (3, 18, "Tesoro"), (3, 19, "Periférico Sur");

/*LINEA 4*/
insert into registro(idL, idE, nombre) values 
	(4, 1, "Central Sur"), (4, 2, "Vallarta"), (4, 3, "Jardines de la Paz"), (4, 4, "U. Panamericana"),
    (4, 5, "Juan Palomar"), (4, 6, "Seminario"), (4, 7, "Cámara de Comercio"), (4, 8, "Minerva"),
    (4, 9, "Centro Magno"), (4, 10, "Américas"), (4, 11, "Chapultepec"), (4, 12, "Paraninfo"),
    (4, 13, "Juárez"), (4, 14, "Plaza Universidad"), (4, 15, "Sand Juan de Dios"), (4, 16, "Belisario Domínguez"),
    (4, 17, "Oblatos 2"), (4, 18, "Cristobal de Oñate"), (4, 19, "San Andrés"), (4, 20, "San Jacinto"),
    (4, 21, "La Aurora"), (4, 22, "Tetlán");

/*LINEA 5*/
insert into registro(idL, idE, nombre) values 
	(5, 1, "San Isidro"), (5, 2, "CUCEA"), (5, 3, "Parque"), (5, 4, "Seatle"),
    (5, 5, "Zoquipan"), (5, 6, "Country"), (5, 7, "Hospital General"), (5, 8, "Plaza Patria"),
    (5, 9, "Colomos"), (5, 10, "Plaza Pabellón"), (5, 11, "San Javier"), (5, 12, "3 de Marzo"),
    (5, 13, "Jardines Universidad"), (5, 14, "Ferrocarril"), (5, 15, "Seminario"), (5, 16, "La Gran Plaza"),
    (5, 17, "San Ignacio"), (5, 18, "Estampida"), (5, 19, "Chapalita"), (5, 20, "Abastos"),
    (5, 21, "Mandarina"), (5, 22, "Ruiseñor"), (5, 23, "Unidad Deportiva"), (5, 24, "Plaza las Torres"),
    (5, 25, "Cristo Rey"), (5, 26, "El Dean"), (5, 27, "Nogalera"), (5, 28, "Álamo"),
    (5, 29, "Textiles");

/*LINEA 6*/
insert into registro(idL, idE, nombre) values 
	(6, 1, "Tabachines"), (6, 2, "Centro Cultural"), (6, 3, "Zoquipan"), (6, 4, "Patria"),
    (6, 5, "División del Norte"), (6, 6, "Lomas"), (6, 7, "Plan de San Luis"), (6, 8, "Colón"),
    (6, 9, "Jose María Vigil"), (6, 10, "Zarco"), (6, 11, "Av. México"), (6, 12, "Ladrón de Guevara"),
    (6, 13, "Américas"), (6, 14, "Lafayette"), (6, 15, "Chapu"), (6, 16, "Monumento"),
    (6, 17, "Santa Eduwiges"), (6, 18, "Día"), (6, 19, "Abastos"), (6, 20, "Parque de las Estrellas"),
    (6, 21, "Expo"), (6, 22, "Plaza del Sol");

select * from registro where idL=6;
-- delete from registro;