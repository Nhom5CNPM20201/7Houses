-- insert TaiKhoan
insert into TaiKhoan(Username, Password, ChucVu, Hoten) 
values 
('admin', '210cf7aa5e2682c9c9d4511f88fe2789', 0, 'Nguyễn Văn A'),
('admin1', 'e10adc3949ba59abbe56e057f20f883e', 1, 'Nguyễn Tuấn B'),
('kt', 'e10adc3949ba59abbe56e057f20f883e', 2, 'Trần Thị C');

-- insert DiaChi
insert into DiaChiTT(SoNha, DuongPho, Phuong, Quan, ThanhPho)
values
(13, N'Minh Khai', N'Hoàng Văn Thụ', N'Hoàng Mai', N'Hà Nội'),
(20, N'Thanh Nhàn', N'Thanh Nhàn', N'Hai Bà Trưng', N'Hà Nội'),
(29, N'Trương Định', N'Trương Định', N'Trương Định', N'Hà Nội'),
(30, N'Ô Chợ Dừa', N'Xã Đàn', N'Thanh Xuân', N'Hà Nội'),
(13, N'Hà Đông', N'Hà Đông', N'Thanh Xuân', N'Hà Nội'),
(2, N'Hoàng Khương', N'Hoàng Khương', N'Thanh Ba', N'Phú Thọ');

-- insert NhanKhau
insert into NhanKhau(HoTen, NoiSinh, NgheNghiep, NgaySinh, NgayDK, QuanHeCH

-- insert HoKhau
insert into HoKhau(IdDiaChi, SoHoKhau,