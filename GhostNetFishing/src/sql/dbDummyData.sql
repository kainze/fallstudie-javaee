USE ghost_net_fishing;

-- Benutzer
INSERT INTO user (user_id, username, password, firstname, lastname, email, phone, rescuer, user_disabled, insert_by, insert_date)
VALUES
    -- Admin-Benutzer
    (UUID(), 'admin', 'hashed_admin_password', 'Admin', 'User', 'admin@example.com', '1234567890', FALSE, FALSE, NULL, NOW()),
    -- Anonymer meldender Benutzer
    (UUID(), 'anonymous', NULL, 'Anonymous', 'User', NULL, NULL, FALSE, FALSE, NULL, NOW()),
    -- Bergender Benutzer
    (UUID(), 'rescuer', 'hashed_rescuer_password', 'John', 'Berger', 'John@Berger.com', '0987654321', TRUE, FALSE, NULL, NOW());

-- ghostnet_state-Einträge
INSERT INTO ghostnet_state (ghostnet_state_id, name, description, insert_by, insert_date)
VALUES
    (UUID(), 'Gemeldet', 'Eine meldende Person hat das Geisternetz im System erfasst.', 
        (SELECT user_id FROM user WHERE username = 'admin'), NOW()),
    (UUID(), 'Bergung bevorstehend', 'Eine bergende Person hat die Bergung angekündigt.', 
        (SELECT user_id FROM user WHERE username = 'admin'), NOW()),
    (UUID(), 'Geborgen', 'Eine bergende Person hat das Geisternetz erfolgreich geborgen.', 
        (SELECT user_id FROM user WHERE username = 'admin'), NOW()),
    (UUID(), 'Verschollen', 'Eine beliebige Person hat festgestellt, dass das Geisternetz am gemeldeten Standort nicht auffindbar ist.', 
        (SELECT user_id FROM user WHERE username = 'admin'), NOW());

-- Dummy-Einträge in der Tabelle ghostnet
INSERT INTO ghostnet (ghostnet_id, gps_latitude, gps_longitude, size, ghostnet_state_id, notifier, rescuer, insert_by, insert_date)
VALUES
    -- Geisternetz 1: Gemeldet, kein Rescuer zugewiesen
    (UUID(), 5234567, 1345678, 10, 
        (SELECT ghostnet_state_id FROM ghostnet_state WHERE name = 'Gemeldet'),
        (SELECT user_id FROM user WHERE username = 'anonymous'),
        NULL,
        (SELECT user_id FROM user WHERE username = 'admin'),
        NOW()),

    -- Geisternetz 2: Bergung bevorstehend, mit Rescuer
    (UUID(), 4832345, 1034567, 20, 
        (SELECT ghostnet_state_id FROM ghostnet_state WHERE name = 'Bergung bevorstehend'),
        (SELECT user_id FROM user WHERE username = 'anonymous'),
        (SELECT user_id FROM user WHERE username = 'rescuer'),
        (SELECT user_id FROM user WHERE username = 'admin'),
        NOW()),

    -- Geisternetz 3: Geborgen, mit Rescuer
    (UUID(), 5032123, 1245678, 15, 
        (SELECT ghostnet_state_id FROM ghostnet_state WHERE name = 'Geborgen'),
        (SELECT user_id FROM user WHERE username = 'anonymous'),
        (SELECT user_id FROM user WHERE username = 'rescuer'),
        (SELECT user_id FROM user WHERE username = 'admin'),
        NOW());
        