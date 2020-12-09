Create database QLDC;
go
use QLDC;

Create table NhanKhau(
	Id int not null primary key identity(1,1),
	IdHoKhau int,
	HoTen nvarchar(255),
	BiDanh nvarchar(255),
	NoiSinh nvarchar(255),
	NgheNghiep nvarchar(255),
	NgaySinh date,
	NgayDK date,
	QuanHeCH tinyint,
	DanToc nvarchar(100),
	GioiTinh tinyint,
	NoiLamViec nvarchar(255),
	SoCMND char(20),
	NgayCapCMND date,
	NoiCapCMND nvarchar(255),
)

create table TamTruVang(
	IdNhanKhau int not null,
	IdDiaChi int not null,
	ThoiGian date not null,
	BatDau date,
	KetThuc date,
	TheLoai tinyint,
	ThongTinThem ntext,
	constraint PK_TamTruVang primary key(IdNhanKhau, ThoiGian),
)

create table ThayDoiKhac(
	Id int not null primary key identity(1,1),
	IdHoKhau int,
	IdNhanKhau int,
	ThoiGian date,
	TheLoai tinyint,
	NoiDung ntext,
)

create table HoKhau(
	Id int not null primary key identity(1,1),
	IdDiaChi int,
	SoHoKhau char(20),
	IdChuHoKhau	int,
	HoTenChuHo nvarchar(255),
	NgayDK date
)

create table DiChuyen(
	Id int not null primary key identity(1,1),
	IdHoKhau int,
	IdNhanKhau int,
	IdDiaChiCu int,
	IdDiaChiMoi int,
	NgayChuyen date,
	TheLoai tinyint,
)

create table DiaChiTT(
	Id int not null primary key identity(1,1),
	SoNha int,
	DuongPho nvarchar(100),
	Phuong nvarchar(100),
	Quan nvarchar(100),
	ThanhPho nvarchar(100),
	ThongTinThem text
)

create table KhoanPhi(
	Id int not null primary key identity(1,1),
	TheLoai tinyint,
	TenKhoanPhi nvarchar(255),
	SoTien money,
	ThongTinThem ntext,
)

create table DongGop(
	Id int not null primary key identity(1,1),
	IdKhoanPhi int,
	IdHoKhau int,
	SoTien money,
	ThoiGian date,
	ThongTinThem ntext,
)

create table TaiKhoan(
	Id int not null primary key identity(1,1),
	Username char(30),
	Password char(100),
	ChucVu tinyint,
	Hoten nvarchar(255),
)

alter table TamTruVang
add constraint FK_TamTruVang_NhanKhau foreign key(IdNhanKhau) references NhanKhau(Id);
alter table ThayDoiKhac
add constraint FK_ThayDoiKhac_HoKhau foreign key(IdHoKhau) references HoKhau(Id);
alter table ThayDoiKhac
add constraint FK_ThayDoiKhac_NhanKhau foreign key(IdNhanKhau) references NhanKhau(Id);
alter table HoKhau
add constraint FK_HoKhau_DiaChiTT foreign key(IdDiaChi) references DiaChiTT(Id);
alter table HoKhau
add constraint FK_HoKhau_NhanKhau foreign key(IdChuHoKhau) references NhanKhau(Id);
alter table DiChuyen
add constraint FK_DiChuyen_NhanKhau foreign key(IdNhanKhau) references NhanKhau(Id);
alter table DiChuyen
add constraint FK_DiChuyen_HoKhau foreign key(IdHoKhau) references HoKhau(Id);
alter table DiChuyen
add constraint FK_DiChuyen_DiaChiCu foreign key(IdDiaChiCu) references DiaChiTT(Id);
alter table DiChuyen
add constraint FK_DiChuyen_DiaChiMoi foreign key(IdDiaChiMoi) references DiaChiTT(Id);
alter table DongGop
add constraint FK_DongGop_HoKhau foreign key(IdHoKhau) references HoKhau(Id);
alter table DongGop
add constraint FK_DongGop_KhoanPhi foreign key(IdKhoanPhi) references KhoanPhi(Id);
