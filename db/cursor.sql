BEGIN;
--Объявление курсора с возможностью скролла в 2 направлениях
DECLARE cursor_name SCROLL  cursor for select * from products;
--Прочитать последнюю строку
fetch LAST from cursor_name;
--Прочитать следующую с конца строку
fetch BACKWARD from cursor_name;
--Получить следующие 4 строки после 1 ласт и 1 беквард
fetch BACKWARD 4 from cursor_name;
--Перепрыгнуть ещё на 4 строки в обратном направлении
move backward 4 from cursor_name;
--И того 1+1+4+4 = 10. Получить 10 строку из 20
fetch BACKWARD from cursor_name;
--Перепрыгнуть на 15 строку в прямом направлении
move forward 5 from cursor_name;
--Прочитать 16 и 17 строки
fetch forward 2 from cursor_name;
--Прочитать 16 и 15 строки
fetch backward 2 from cursor_name;
close cursor_name;
commit;
