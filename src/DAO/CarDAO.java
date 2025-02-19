package DAO;

import Models.Car;
import Models.Person;

import java.sql.Connection;
import java.util.List;


public class CarDAO implements GenericDAO<Car>
{
    private Connection connection;

    public CarDAO(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void insert(Car car)
    {

    }

    @Override
    public void update(Car car)
    {

    }


    @Override
    public void delete(Car car)
    {

    }

    @Override
    public void selectById(Car car)
    {

    }

    public List<Car> selectAll()
    {
        return ;
    }


}
