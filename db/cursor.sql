BEGIN;
--���������� ������� � ������������ ������� � 2 ������������
DECLARE cursor_name SCROLL  cursor for select * from products;
--��������� ��������� ������
fetch LAST from cursor_name;
--��������� ��������� � ����� ������
fetch BACKWARD from cursor_name;
--�������� ��������� 4 ������ ����� 1 ���� � 1 �������
fetch BACKWARD 4 from cursor_name;
--������������ ��� �� 4 ������ � �������� �����������
move backward 4 from cursor_name;
--� ���� 1+1+4+4 = 10. �������� 10 ������ �� 20
fetch BACKWARD from cursor_name;
--������������ �� 15 ������ � ������ �����������
move forward 5 from cursor_name;
--��������� 16 � 17 ������
fetch forward 2 from cursor_name;
--��������� 16 � 15 ������
fetch backward 2 from cursor_name;
close cursor_name;
commit;
