CREATE TABLE Client (
	ClientID int identity(1,1) primary key not null
	, Sirname nvarchar(255)
	, [Name] nvarchar(125)
	, PhoneNumber nvarchar(50) not null unique
	, Discount money
);

create table Dish (
	DishID int identity(1,1) primary key not null
	, [Name] nvarchar(255) not null
	, CategoryID nvarchar(255)
	, Price money not null
	, [Weight] money
	, UnitID int
)

create table [Order] (
	OrderID int identity(1,1) primary key not null
	, ClientId int
	, OrderDate date not null
	, TotalAmount money not null
	, Tax money not null
	, Amount money not null
	, Tips money
)

create table OrderDetails (
	DishID int
	, OrderID int
	, Quantity int not null
)

create table Category (
	CategoryID int identity(1,1) primary key not null
	, CategoryName nvarchar(255) not null unique
)

create table Unit (
	UnitID int identity(1,1) primary key not null
	, UnitName nvarchar(50) not null unique
)

alter table [Order]
add constraint FK_Order_Client_ClientID
foreign key (ClientID) references Client(ClientID)

alter table OrderDetails
add constraint FK_OrderDetails_Dish_DishID
foreign key (DishID) references Dish(DishID)

alter table OrderDetails
add constraint FK_OrderDetails_Dish_OrderID
foreign key (OrderID) references [Order](OrderID)
