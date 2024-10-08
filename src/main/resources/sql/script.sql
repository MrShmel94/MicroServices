CREATE TABLE IF NOT EXISTS site
(
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE,
    place VARCHAR (64) NOT NULL
);

INSERT INTO site (name, place)
VALUES ('GD', 'Gardno'),
       ('GDA', 'Olsztynek');

CREATE TABLE IF NOT EXISTS shift
(
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    site_id INTEGER NOT NULL REFERENCES site,
    UNIQUE(name, site_id)
);

CREATE TABLE IF NOT EXISTS team
(
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(16) NOT NULL,
    site_id INTEGER NOT NULL REFERENCES site,
    UNIQUE(name, site_id)
);

CREATE TABLE IF NOT EXISTS department
(
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL ,
    site_id SMALLINT NOT NULL REFERENCES site,
    UNIQUE(name, site_id)
);


CREATE TABLE IF NOT EXISTS position
(
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    site_id SMALLINT NOT NULL REFERENCES site,
    UNIQUE(name, site_id)
);

CREATE TABLE IF NOT EXISTS county
(
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(128),
    site_id SMALLINT NOT NULL REFERENCES site,
    UNIQUE(name, site_id)
);

CREATE TABLE IF NOT EXISTS agency
(
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    site_id SMALLINT NOT NULL REFERENCES site,
    UNIQUE(name, site_id)
);

DO $$
    DECLARE
        gd_id INT;
        gda_id INT;
    BEGIN
        SELECT id INTO gd_id FROM site WHERE name = 'GD';
        SELECT id INTO gda_id FROM site WHERE name = 'GDA';

        INSERT INTO shift(name, site_id)
        VALUES ('A', gd_id),
               ('A', gda_id),
               ('B', gd_id),
               ('B', gda_id),
               ('Night', gd_id),
               ('Parents', gd_id);

        INSERT INTO team(name, site_id)
        VALUES ('A', gd_id),
               ('B', gd_id),
               ('C', gd_id),
               ('D', gd_id),
               ('E', gd_id),
               ('F', gd_id),
               ('G', gd_id),
               ('H', gd_id),
               ('R', gd_id),
               ('J', gd_id),
               ('N', gd_id),
               ('A', gda_id),
               ('B', gda_id),
               ('C', gda_id),
               ('D', gda_id),
               ('E', gda_id),
               ('F', gda_id),
               ('G', gda_id),
               ('H', gda_id);

        INSERT INTO department(name, site_id)
        VALUES ('Unloading', gd_id),
               ('Shipping', gd_id),
               ('Receive', gd_id),
               ('Pick/Stow', gd_id),
               ('Sort/Pack', gd_id),
               ('Return', gd_id),
               ('Quality', gd_id),
               ('Administration', gd_id),
               ('Unloading', gda_id),
               ('Shipping', gda_id),
               ('Receive', gda_id),
               ('Pick/Stow', gda_id),
               ('Sort/Pack', gda_id),
               ('Return', gda_id),
               ('Quality', gda_id),
               ('Administration', gda_id);

        INSERT INTO position(name, site_id)
        VALUES ('Warehouseman', gd_id),
               ('Quality Specialist', gd_id),
               ('Support', gd_id),
               ('PWTZ', gd_id),
               ('Mentor', gd_id),
               ('Problem Solver', gd_id),
               ('Senior Problem Solver', gd_id),
               ('Team Leader', gd_id),
               ('Area Managers', gd_id),
               ('Administration Specialist', gd_id),
               ('Senior Controlling Specialist', gd_id),
               ('Independent Accountant', gd_id),
               ('Quality Engineer', gd_id),
               ('Quality Manager', gd_id),
               ('External Services Manager', gd_id),
               ('Purchasing Manager', gd_id),
               ('External Services Specialist', gd_id),
               ('External Maintenance Specialist', gd_id),
               ('Security Manager', gd_id),
               ('HR and Payroll Manager', gd_id),
               ('Process Specialist', gd_id),
               ('HR Manager', gd_id),
               ('Senior HR Specialist', gd_id),
               ('HR Specialist', gd_id),
               ('Junior HR Specialist', gd_id),
               ('Translator', gd_id),
               ('Senior HR and Payroll Specialist', gd_id),
               ('HR and Payroll Specialist', gd_id),
               ('Data Analysis Specialist', gd_id),
               ('Health and Safety Manager', gd_id),
               ('Health and Safety Specialist', gd_id),
               ('Health and Safety Inspector', gd_id),
               ('Employer Brand Manager', gd_id),
               ('Operation Managers', gd_id),
               ('HR Site Managers', gd_id),
               ('Site Managers', gd_id),
               ('Warehouseman', gda_id),
               ('Quality Specialist', gda_id),
               ('Support', gda_id),
               ('PWTZ', gda_id),
               ('Mentor', gda_id),
               ('Problem Solver', gda_id),
               ('Senior Problem Solver', gda_id),
               ('Team Leader', gda_id),
               ('Area Managers', gda_id),
               ('Administration Specialist', gda_id),
               ('Senior Controlling Specialist', gda_id),
               ('Independent Accountant', gda_id),
               ('Quality Engineer', gda_id),
               ('Quality Manager', gda_id),
               ('External Services Manager', gda_id),
               ('Purchasing Manager', gda_id),
               ('External Services Specialist', gda_id),
               ('External Maintenance Specialist', gda_id),
               ('Security Manager', gda_id),
               ('HR and Payroll Manager', gda_id),
               ('Process Specialist', gda_id),
               ('HR Manager', gda_id),
               ('Senior HR Specialist', gda_id),
               ('HR Specialist', gda_id),
               ('Junior HR Specialist', gda_id),
               ('Translator', gda_id),
               ('Senior HR and Payroll Specialist', gda_id),
               ('HR and Payroll Specialist', gda_id),
               ('Data Analysis Specialist', gda_id),
               ('Health and Safety Manager', gda_id),
               ('Health and Safety Specialist', gda_id),
               ('Health and Safety Inspector', gda_id),
               ('Employer Brand Manager', gda_id),
               ('Operation Managers', gda_id),
               ('HR Site Managers', gda_id),
               ('Site Managers', gda_id);

        INSERT INTO county(name, site_id)
        VALUES ('Poland', gd_id),
               ('Belarus', gd_id),
               ('Ukraine', gd_id),
               ('Georgia', gd_id),
               ('Poland', gda_id),
               ('Belarus', gda_id),
               ('Ukraine', gda_id),
               ('Georgia', gda_id);

        INSERT INTO agency(name, site_id)
        VALUES ('APS', gd_id),
               ('Bisar', gd_id),
               ('APS', gda_id),
               ('Bisar', gda_id);

    END $$;



CREATE TABLE IF NOT EXISTS user_registration
(
    id BIGSERIAL PRIMARY KEY,
    expertis SMALLINT NOT NULL UNIQUE,
    email VARCHAR(256) NOT NULL UNIQUE,
    encrypted_password VARCHAR(256) NOT NULL,
    is_verified BOOLEAN DEFAULT FALSE NOT NULL,
    email_verification_token VARCHAR(256),
    email_verification_status BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE IF NOT EXISTS role(
    id SERIAL PRIMARY KEY,
    name VARCHAR (64) NOT NULL UNIQUE
);

INSERT INTO role (name)
VALUES ('Admin'),
       ('Operations Manager'),
       ('Manager'),
       ('Team Leader'),
       ('HR Specialist'),
       ('Mentor'),
       ('User');

CREATE TABLE IF NOT EXISTS employee
(
    id BIGSERIAL PRIMARY KEY,
    expertis VARCHAR(128) NOT NULL UNIQUE,
    zalos_id SMALLINT UNIQUE,
    br_code VARCHAR(128) UNIQUE,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    is_work BOOLEAN DEFAULT TRUE NOT NULL,
    sex VARCHAR(32) NOT NULL,
    team_id SMALLINT NOT NULL REFERENCES team,
    site_id SMALLINT NOT NULL REFERENCES site,
    shift_id SMALLINT NOT NULL REFERENCES shift,
    country_id SMALLINT NOT NULL REFERENCES county,
    department_id SMALLINT NOT NULL REFERENCES department,
    position_id SMALLINT NOT NULL REFERENCES position,
    supervisor_id SMALLINT REFERENCES employee,
    is_supervisor BOOLEAN DEFAULT FALSE,
    agency_id SMALLINT NOT NULL REFERENCES agency,
    valid_from TIMESTAMP DEFAULT NOW() NOT NULL,
    valid_to TIMESTAMP DEFAULT '9999-12-31 23:59:59'::TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE INDEX idx_employee_expertis ON employee (expertis);
CREATE INDEX idx_employee_supervisor_id ON employee (supervisor_id);
CREATE INDEX idx_employee_site_dept_shift_team ON employee (site_id, department_id, shift_id, team_id);

CREATE TABLE IF NOT EXISTS email_supervisor
(
    id SERIAL PRIMARY KEY,
    email VARCHAR(256) NOT NULL UNIQUE,
    employee_id INTEGER REFERENCES employee
);

CREATE TABLE IF NOT EXISTS phone_supervisor
(
    id SERIAL PRIMARY KEY,
    phone_number VARCHAR(128) NOT NULL UNIQUE,
    employee_id INTEGER REFERENCES employee
);

CREATE TABLE user_role (
    user_id BIGINT REFERENCES employee(id),
    role_id BIGINT REFERENCES role(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS ai_employee
(
    id BIGSERIAL PRIMARY KEY,
    note VARCHAR(1028),
    date_start_contract DATE NOT NULL,
    date_finish_contract DATE NOT NULL,
    date_BHP_now DATE NOT NULL,
    date_BHP_future DATE NOT NULL,
    date_ADR_now DATE NOT NULL,
    date_ADR_future DATE NOT NULL,
    employee_id BIGINT NOT NULL REFERENCES employee
);

CREATE TABLE IF NOT EXISTS employee_history
(
    audit_id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    expertis VARCHAR(128) NOT NULL,
    zalos_id SMALLINT,
    br_code VARCHAR(128),
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    is_work BOOLEAN DEFAULT TRUE NOT NULL,
    sex VARCHAR(32) NOT NULL,
    team_id SMALLINT NOT NULL,
    site_id SMALLINT NOT NULL,
    shift_id SMALLINT NOT NULL,
    country_id SMALLINT NOT NULL,
    department_id SMALLINT NOT NULL,
    position_id SMALLINT NOT NULL,
    supervisor_id SMALLINT,
    is_supervisor BOOLEAN DEFAULT FALSE,
    agency_id SMALLINT NOT NULL,
    valid_from TIMESTAMP NOT NULL,
    valid_to TIMESTAMP NOT NULL,
    operation VARCHAR(10) NOT NULL,
    changed_by BIGINT NOT NULL,
    changed_at TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE OR REPLACE FUNCTION employee_audit_trigger()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO employee_history (
            employee_id, expertis, zalos_id, br_code, first_name, last_name,
            sex, is_work, team_id, site_id, shift_id, country_id, department_id, position_id,
            supervisor_id, is_supervisor, agency_id, valid_from, valid_to,
            operation, changed_by, changed_at
        ) VALUES (
                     NEW.id, NEW.expertis, NEW.zalos_id, NEW.br_code, NEW.first_name, NEW.last_name,
                     NEW.sex, NEW.is_work, NEW.team_id, NEW.site_id, NEW.shift_id, NEW.country_id, NEW.department_id, NEW.position_id,
                     NEW.supervisor_id, NEW.is_supervisor, NEW.agency_id, NEW.valid_from, NEW.valid_to,
                     'INSERT', current_setting('app.current_user_id')::BIGINT, NOW()
                 );
        RETURN NEW;

    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO employee_history (
            employee_id, expertis, zalos_id, br_code, first_name, last_name,
            sex, is_work, team_id, site_id, shift_id, country_id, department_id, position_id,
            supervisor_id, is_supervisor, agency_id, valid_from, valid_to,
            operation, changed_by, changed_at
        ) VALUES (
                     NEW.id, NEW.expertis, NEW.zalos_id, NEW.br_code, NEW.first_name, NEW.last_name,
                     NEW.sex, NEW.is_work,NEW.team_id, NEW.site_id, NEW.shift_id, NEW.country_id, NEW.department_id, NEW.position_id,
                     NEW.supervisor_id, NEW.is_supervisor, NEW.agency_id, NEW.valid_from, NEW.valid_to,
                     'UPDATE', current_setting('app.current_user_id')::BIGINT, NOW()
                 );
        RETURN NEW;
    END IF;
END;
$$;

CREATE TRIGGER employee_audit
    AFTER INSERT OR UPDATE
    ON employee
    FOR EACH ROW
EXECUTE PROCEDURE employee_audit_trigger();