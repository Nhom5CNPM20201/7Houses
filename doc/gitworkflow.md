Git workflow of this app.

1. Xác định nhánh của mình là gì, ví dụ: 'xxxxx_hung_yy' (tạo một service để quản lý nhân khẩu) (xxxxx
là tên sprint hiện tại, yy là số thứ tự của ticket trên workboard)
2. Checkout nhánh release (lưu ý:nhớ fetch và pull code tránh trường hợp không 
cập nhật được code mới)
3. Tạo nhánh mới. vd: 'sprint5_hung_16' 
4. Thay đổi mã nguồn của ứng dụng và test trên chính máy mình.
5. Sau đó, Commit chúng và đẩy lên github (push) nhánh của mình. vd: 'sprint5_hung_16'
6. Thông báo mình đã hoàn thành và đẩy code lên git.

-------------- 
1. Receive a ticket. Ex: 'xxxxx_hung_yy' (Create a new service for manage people)
2. Checkout 'release'.
3. Create a new branch 'db_hung'
4. Make some changes, and test them.
5. After changes, commit and push them to your current branch (db_hung) 
6. Create MR (merge request) from 'db_hung' into 'Development'
and assign to 'snowphantom' (Hung Nguyen) or raise your problems in group
for support.
7. After merge 'db_hung' into 'Development', start testing and make it alright
before merging to release.
8. Merge 'db_hung' into Release_sprintX (X: current sprint number) and test
in release_sprintX


