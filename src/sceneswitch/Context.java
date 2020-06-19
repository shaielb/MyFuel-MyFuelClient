package sceneswitch;

import db.entity.Customer;
import db.entity.Employee;
import db.entity.Person;
import db.entity.SystemUser;

/**
 * @author shaielb
 *
 */
public class Context {

	/**
	 * 
	 */
	private SystemUser _systemUser;
	
	/**
	 * 
	 */
	private Employee _employee;
	
	/**
	 * 
	 */
	private Customer _customer;
	
	/**
	 * 
	 */
	private Person _person;
	
	public void clear() {
		_systemUser = null;
		_employee = null;
		_customer = null;
		_person = null;
	}
	
	/**
	 * @return
	 */
	public SystemUser getSystemUser() {
		return _systemUser;
	}
	
	/**
	 * @param su
	 */
	public void setSystemUser(SystemUser su) {
		_systemUser = su;
	}

	public Employee getEmployee() {
		return _employee;
	}

	public void setEmployee(Employee employee) {
		_employee = employee;
	}

	public Customer getCustomer() {
		return _customer;
	}

	public void setCustomer(Customer customer) {
		_customer = customer;
	}

	public Person getPerson() {
		return _person;
	}

	public void setPerson(Person person) {
		_person = person;
	}
}
