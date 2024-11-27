USE ghost_net_fishing;

-- Benutzer
INSERT INTO Users (username, password, firstname, lastname, email, phone, rescuer, userdisabled, insertby_userId, insertdate)
VALUES
    -- Admin-Benutzer
    ('admin', 'Qkfc1gQyvIvHUV9MANsYD/A4P2PJ2UJfkEkNScF8xsg=', 'Admin', 'User', 'admin@example.com', '1234567890', FALSE, FALSE, NULL, NOW()),
    -- Bergende Benutzer (Rescuers)
    ('rescuer1', 'wjqS7lNLK6HOJ8nxhWhD+iF10TxMzcePxDDYwbTbavk=', 'Hans', 'Schneider', 'hans.schneider@example.com', '01511234567', TRUE, FALSE, NULL, NOW()),
    ('rescuer2', 'wjqS7lNLK6HOJ8nxhWhD+iF10TxMzcePxDDYwbTbavk=', 'Friedrich', 'Meier', 'friedrich.meier@example.com', '01511234568', TRUE, FALSE, NULL, NOW()),
    ('rescuer3', 'wjqS7lNLK6HOJ8nxhWhD+iF10TxMzcePxDDYwbTbavk=', 'Wolfgang', 'Koch', 'wolfgang.koch@example.com', '01511234569', TRUE, FALSE, NULL, NOW()),
    -- Meldende Benutzer (Notifiers)
    ('notifier1', 'wjqS7lNLK6HOJ8nxhWhD+iF10TxMzcePxDDYwbTbavk=', 'Sophie', 'Fischer', 'sophie.fischer@example.com', '01601234567', FALSE, FALSE, NULL, NOW()),
    ('notifier2', 'wjqS7lNLK6HOJ8nxhWhD+iF10TxMzcePxDDYwbTbavk=', 'Anna', 'Weber', 'anna.weber@example.com', '01601234568', FALSE, FALSE, NULL, NOW()),
    ('notifier3', 'wjqS7lNLK6HOJ8nxhWhD+iF10TxMzcePxDDYwbTbavk=', 'Klara', 'Bauer', 'klara.bauer@example.com', '01601234569', FALSE, FALSE, NULL, NOW());


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
