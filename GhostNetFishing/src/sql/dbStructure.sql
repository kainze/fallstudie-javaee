CREATE DATABASE IF NOT EXISTS ghost_net_fishing;


-- do not need that, this is created by the Application
USE ghost_net_fishing;

-- Tabelle: user
CREATE TABLE IF NOT EXISTS user (
    userId UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    rescuer BOOLEAN DEFAULT FALSE,
    userDisabled BOOLEAN DEFAULT FALSE,
    insertBy UUID,
    insertDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleteBy UUID,
    deleteDate DATETIME,
    FOREIGN KEY (insertBy) REFERENCES user (userId),
    FOREIGN KEY (deleteBy) REFERENCES user (userId)
);

-- Tabelle: ghostnet_state
CREATE TABLE IF NOT EXISTS ghostnet_state (
    ghostnetStateId UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    insertBy UUID,
    insertDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleteBy UUID,
    deleteDate DATETIME,
    FOREIGN KEY (insertBy) REFERENCES user (userId),
    FOREIGN KEY (deleteBy) REFERENCES user (userId)
);

-- Tabelle: ghostnet
CREATE TABLE IF NOT EXISTS ghostnet (
    ghostnetId UUID PRIMARY KEY,
    gpsLatitude INT NOT NULL,
    gpsLongitude INT NOT NULL,
    size INT,
    ghostnetStateId UUID NOT NULL,
    notifier UUID NOT NULL,
    rescuer UUID,
    insertBy UUID,
    insertDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleteBy UUID,
    deleteDate DATETIME,
    FOREIGN KEY (ghostnetStateId) REFERENCES ghostnet_state (ghostnetStateId),
    FOREIGN KEY (notifier) REFERENCES user (user_id),
    FOREIGN KEY (rescuer) REFERENCES user (user_id),
    FOREIGN KEY (insertBy) REFERENCES user (userId),
    FOREIGN KEY (deleteBy) REFERENCES user (userId)
);

/* 
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
); */
