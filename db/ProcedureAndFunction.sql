create or replace procedure delete_data(u_id integer, u_price integer)
language 'plpgsql'
as
$$
    BEGIN
    delete from products
    where id = u_id and price = u_price;
    END
$$;

create or replace function delete_function(u_id integer)
returns void
language 'plpgsql'
as
$$
declare result integer;
    BEGIN
    delete from products where price > 66;
    END;
$$;