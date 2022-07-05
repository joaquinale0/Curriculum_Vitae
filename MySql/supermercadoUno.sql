create database supermercadoUno character set utf8mb4;
use supermercadoUno;

create table if not exists Usuario(
	idUsuairo INT AUTO_INCREMENT,
	nombreUsuario VARCHAR(45) NOT NULL unique,
	password VARCHAR(45) not NULL,
	permisos TINYINT,
	PRIMARY KEY (idUsuairo)
);



CREATE TABLE IF NOT EXISTS Cliente(
  idCliente INT auto_increment,
  nombre VARCHAR(45) DEFAULT 'Sin nombre',
  apellido VARCHAR(45) DEFAULT 'sin apellido',
  dni INT,
  domicilio VARCHAR(45) DEFAULT 'Sin Domicilio',
  telefono INT default 3879,
  pais VARCHAR(45) DEFAULT 'Argentina',
  provincia VARCHAR(45) DEFAULT 'Salta',
  codigoPostal INT DEFAULT 12234,
  cliente_es_usuario INT,
  PRIMARY KEY (idCliente),
  foreign key (cliente_es_usuario) references Usuario(idUsuairo)
);

CREATE TABLE IF NOT EXISTS Administrador(
  idAdministrador INT auto_increment,
  nombre VARCHAR(45) DEFAULT 'Sin nombre',
  apellido VARCHAR(45) DEFAULT 'Sin apellido',
  administrador_es_usuario INT,
  Producto_idProducto INT,
  PRIMARY KEY (idAdministrador),
  FOREIGN KEY (administrador_es_usuario ) references Usuario(idUsuairo),
  FOREIGN KEY (Producto_idProducto) references Producto(idProducto)
  );
  
  CREATE TABLE IF NOT EXISTS Producto(
  idProducto INT AUTO_INCREMENT,
  nombre VARCHAR(45) DEFAULT 'Sin nombre',
  cantidad INT DEFAULT 0,
  precioUnitario FLOAT DEFAULT 0,
  marca VARCHAR(45) DEFAULT 'Sin marca',
  descripcion VARCHAR(250) DEFAULT 'sin decsripcion',
  PRIMARY KEY (idProducto)  
  );
  
  CREATE TABLE Ticket (
	idTicket int auto_increment,
	Cliente_idCliente INT,
    Cliente_idUsuario int,
	Producto_idProducto INT,
	
    primary key (idTicket),
    
    FOREIGN KEY (Cliente_idCliente) references Cliente (idCliente),
    foreign key (Cliente_idUsuario) references Usuario (idUsuairo),
    FOREIGN KEY (Producto_idProducto) REFERENCES Producto (idProducto)
    );