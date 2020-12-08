-- insert TaiKhoan
insert into TaiKhoan(Username, Password, ChucVu, Hoten) 
values 
('admin', '21232f297a57a5a743894a0e4a801fc3', 0, N'Nguyễn Văn A'),
('admin1', 'e10adc3949ba59abbe56e057f20f883e', 1, N'Nguyễn Tuấn B'),
('kt', 'e10adc3949ba59abbe56e057f20f883e', 2, N'Trần Thị C');

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
insert into NhanKhau(HoTen, NoiSinh, NgheNghiep, NgaySinh, NgayDK, DanToc, GioiTinh, NoiLamViec, SoCMND, NgayCapCMND, NoiCapCMND)
values
(N'Nguyễn Quang Hùng', N'Nam Tiến, Tiền Hải, Thái Bình', N'Sinh viên', '2000-09-15', '2020-11-29', N'Kinh', 0, N'Hoàn Kiếm, Hà Nội', '034200001548', '2015-09-06', N'Thái Bình'),
(N'Nguyễn Văn Tuấn', N'Nam Tiến, Tiền Hải, Lạng Sơn', N'Làm vườn', '2000-06-11', '2020-11-30', N'Kinh', 0, N'Xuân Thủy, Hà Nội', '034200001128', '2015-10-19', N'Hà Nam')
-- insert HoKhau
insert into HoKhau(IdDiaChi, SoHoKhau, IdChuHoKhau, HoTenChuHo, NgayDK) values
(1, 'HK0001', 1, N'Nguyễn Quang Hùng', '2020-12-07'),
(2, 'HK0002', 2, N'Nguyễn Văn Tuấn', '2020-12-01')
