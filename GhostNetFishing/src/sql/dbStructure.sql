CREATE DATABASE IF NOT EXISTS ghost_net_fishing;

USE ghost_net_fishing;

-- Tabelle: user
CREATE TABLE IF NOT EXISTS user (
    user_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    rescuer BOOLEAN DEFAULT FALSE,
    user_disabled BOOLEAN DEFAULT FALSE,
    insert_by UUID,
    insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    delete_by UUID,
    delete_date DATETIME,
    FOREIGN KEY (insert_by) REFERENCES user (user_id),
    FOREIGN KEY (delete_by) REFERENCES user (user_id)
);

-- Tabelle: ghostnet_state
CREATE TABLE IF NOT EXISTS ghostnet_state (
    ghostnet_state_id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    insert_by UUID,
    insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    delete_by UUID,
    delete_date DATETIME,
    FOREIGN KEY (insert_by) REFERENCES user (user_id),
    FOREIGN KEY (delete_by) REFERENCES user (user_id)
);

-- Tabelle: ghostnet
CREATE TABLE IF NOT EXISTS ghostnet (
    ghostnet_id UUID PRIMARY KEY,
    gps_latitude INT NOT NULL,
    gps_longitude INT NOT NULL,
    size INT,
    ghostnet_state_id UUID NOT NULL,
    notifier UUID NOT NULL,
    rescuer UUID,
    insert_by UUID,
    insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    delete_by UUID,
    delete_date DATETIME,
    FOREIGN KEY (ghostnet_state_id) REFERENCES ghostnet_state (ghostnet_state_id),
    FOREIGN KEY (notifier) REFERENCES user (user_id),
    FOREIGN KEY (rescuer) REFERENCES user (user_id),
    FOREIGN KEY (insert_by) REFERENCES user (user_id),
    FOREIGN KEY (delete_by) REFERENCES user (user_id)
);

-- Tabelle: audit_log
CREATE TABLE IF NOT EXISTS audit_log (
    log_id UUID PRIMARY KEY,
    entity_name VARCHAR(50) NOT NULL,
    entity_id UUID NOT NULL,
    action VARCHAR(20) NOT NULL, 
    performed_by UUID,
    performed_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    FOREIGN KEY (performed_by) REFERENCES user (user_id)
);
