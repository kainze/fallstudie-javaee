USE ghost_net_fishing;

-- Benutzer
INSERT INTO Users (username, password, firstname, lastname, email, phone, rescuer, userdisabled, insertby_userId, insertdate)
VALUES
    -- Admin-Benutzer
    ('admin', 'Qkfc1gQyvIvHUV9MANsYD/A4P2PJ2UJfkEkNScF8xsg=', 'Admin', 'User', 'admin@example.com', '1234567890', FALSE, FALSE, NULL, NOW()),
    -- Bergender Benutzer
    ('rescuer', 'wjqS7lNLK6HOJ8nxhWhD+iF10TxMzcePxDDYwbTbavk=', 'John', 'Berger', 'John@Berger.com', '0987654321', TRUE, FALSE, NULL, NOW());

-- ghostnet_state-Einträge
INSERT INTO GhostNetState (name, description, insertby_userId, insertdate)
VALUES
    ('Gemeldet', 'Eine meldende Person hat das Geisternetz im System erfasst.', 
        (SELECT userid FROM Users WHERE username = 'admin'), NOW()),
    ('Bergung bevorstehend', 'Eine bergende Person hat die Bergung angekündigt.', 
        (SELECT userid FROM Users WHERE username = 'admin'), NOW()),
    ('Geborgen', 'Eine bergende Person hat das Geisternetz erfolgreich geborgen.', 
        (SELECT userid FROM Users WHERE username = 'admin'), NOW()),
    ('Verschollen', 'Eine beliebige Person hat festgestellt, dass das Geisternetz am gemeldeten Standort nicht auffindbar ist.', 
        (SELECT userid FROM Users WHERE username = 'admin'), NOW());

-- Dummy-Einträge in der Tabelle ghostnet
INSERT INTO GhostNet (gpslatitude, gpslongitude, size, ghostnetState_ghostnetStateId, notifier_userId, rescuer_userId, insertby_userId, insertdate)
VALUES
    -- Geisternetz 1: Gemeldet, kein Rescuer zugewiesen
    (5234567, 1345678, 10, 
        (SELECT ghostnetstateid FROM GhostNetState WHERE name = 'Gemeldet'),
        (SELECT userid FROM Users WHERE username = 'anonymous'),
        NULL,
        (SELECT userid FROM Users WHERE username = 'admin'),
        NOW()),
    -- Geisternetz 2: Bergung bevorstehend, mit Rescuer
    (4832345, 1034567, 20, 
        (SELECT ghostnetstateid FROM GhostNetState WHERE name = 'Bergung bevorstehend'),
        (SELECT userid FROM Users WHERE username = 'anonymous'),
        (SELECT userid FROM Users WHERE username = 'rescuer'),
        (SELECT userid FROM Users WHERE username = 'admin'),
        NOW()),
    -- Geisternetz 3: Geborgen, mit Rescuer
    (5032123, 1245678, 15, 
        (SELECT ghostnetstateid FROM GhostNetState WHERE name = 'Geborgen'),
        (SELECT userid FROM Users WHERE username = 'anonymous'),
        (SELECT userid FROM Users WHERE username = 'rescuer'),
        (SELECT userid FROM Users WHERE username = 'admin'),
        NOW());
        