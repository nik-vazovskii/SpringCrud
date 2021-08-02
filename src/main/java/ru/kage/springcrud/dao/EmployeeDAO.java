package ru.kage.springcrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kage.springcrud.models.Employee;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> show() {
        return jdbcTemplate.query( "SELECT * FROM Employee", new BeanPropertyRowMapper<>( Employee.class ) );
    }

    public Employee index(long id ) {
        return jdbcTemplate.query( "SELECT * FROM Employee WHERE id=?", new Object[] { id },  new BeanPropertyRowMapper<>( Employee.class ) )
                .stream()
                .findAny()
                .orElse( null );
    }

    public void save(Employee employee) {
        jdbcTemplate.update( "INSERT INTO Employee ( first_name, last_name, age, email ) VALUES ( ?, ?, ?, ? )",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(),
                employee.getEmail() );

    }

    public void update(long id, Employee updateEmployee) {
        jdbcTemplate.update( "UPDATE Employee SET first_name=?, last_name=?, age=?, email=? WHERE id=?",
                updateEmployee.getFirstName(),
                updateEmployee.getLastName(),
                updateEmployee.getAge(),
                updateEmployee.getEmail(),
                updateEmployee.getId() );
    }

    public void delete(long id) {
        jdbcTemplate.update( "DELETE FROM Employee WHERE id=?", id );
    }
}
