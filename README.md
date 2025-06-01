# Employee-management-system

A Simple Java-based Employee Management System. This project provides functionality to add, update, delete, and view employee records.

## Features

- Add new employee.
- Get employee by id.
- Get all employees in specific department.
- Get all employees wiht pagination support.
- Edit employee details.
- Delete employee.

## API Endpoints

All endpoints are prefixed with `/api/employees`.

### 1. Add New Employee

- **POST** `/api/employees`
- **Request Body:**
  ```json
  {
    "name": "Alice",
    "email": "alice@example.com",
    "departmentName": "HR",
    "password": "securePassword"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1
  }
  ```

### 2. Get Employee by ID

- **GET** `/api/employees/{employeeId}`
- **Response:**
  ```json
  {
    "name": "Alice",
    "email": "alice@example.com",
    "departmentName": "HR"
  }
  ```

### 3. Get All Employees (with Pagination)

- **GET** `/api/employees?page=1&size=3`
- **Response:**
  ```json
  [
    {
      "name": "Alice",
      "email": "alice@example.com",
      "departmentName": "HR"
    },
    ...
  ]
  ```

### 4. Get Employees by Department

- **GET** `/api/employees/department/{departmentName}`

### 5. Update Employee

- **PUT** `/api/employees/{employeeId}`
- **Request Body:** (similar to add)
- **Response:** 202 Accepted

### 6. Delete Employee

- **DELETE** `/api/employees/{employeeId}`
- **Response:** 204 No Content

---

## Example Usage

**Add Employee (curl):**
```sh
curl -X POST http://localhost:8080/api/employees \
  -H 'Content-Type: application/json' \
  -d '{"name":"Bob","email":"bob@example.com","departmentName":"IT","password":"mypassword"}'
```

**Get All Employees:**
```sh
curl http://localhost:8080/api/employees?page=1&size=2
```

**Update Employee:**
```sh
curl -X PUT http://localhost:8080/api/employees/1 \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alice Updated","email":"alice2@example.com","departmentName":"HR"}'
```

---


