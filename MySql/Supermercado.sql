create schema supermercado;
use supermercados;

CREATE TABLE `cliente` (
  `clienteID` int,
  `nombre` varchar(25),
  `apellido` varchar(25),
  `dni` int,
  `domicilio` varchar(25),
  `telefono` int,
  `pais` varchar(25),
  `provincia` varchar(25),
  `codigoPostal` int,
  `carrito` ListaProducto,
  `usuario_fk` Usuario,
  KEY `pk` (`clienteID`),
  KEY `fk` (`usuario_fk`)
);

CREATE TABLE `Producto` (
  `productoID` int,
  `nombre_Producto` varchar(25),
  `cantidad` int,
  `precioUnitario` float,
  `marca` varchar(25),
  `descripcion` varchar(50),
  KEY `pk` (`productoID`)
);

CREATE TABLE `ListaProducto` (
  `listaProductoID` int,
  `lista_Producto` queue<Producto>,
  FOREIGN KEY (`lista_Producto`) REFERENCES `Producto`(`productoID`),
  KEY `pk` (`listaProductoID`),
  KEY `fk` (`lista_Producto`)
);

CREATE TABLE `administrador` (
  `adminID` int,
  `nombre` varchar(25),
  `apellido` varchar(25),
  `usuario_fk` Usuario,
  KEY `pk` (`adminID`),
  KEY `fk` (`usuario_fk`)
);

CREATE TABLE `Usuario` (
  `UsuarioID` int,
  `nombreUsuario` varchar(25),
  `password` varchar(25),
  KEY `pk` (`UsuarioID`)
);

