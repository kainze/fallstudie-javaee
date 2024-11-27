USE ghost_net_fishing;

-- Benutzer
INSERT INTO Users (username, password, firstname, lastname, email, phone, rescuer, userdisabled, insertby_userId, insertdate)
VALUES
    -- Admin-Benutzer
    ('admin', 'Qkfc1gQyvIvHUV9MANsYD/A4P2PJ2UJfkEkNScF8xsg=', 'Admin', 'User', 'admin@example.com', '1234567890', FALSE, FALSE, NULL, NOW()),
    -- Bergende Benutzer (Rescuers)
    ('rescuer1', '7zqHuF9f45GXU5X8BjQ4G+l8unUoqBQvS13zAuUMEZA=', 'Hans', 'Schneider', 'hans.schneider@example.com', '01511234567', TRUE, FALSE, NULL, NOW()),
    ('rescuer2', '9sBBFxACbU+wREBq48IzywUBcctTqQHW9e/xCI6QPwQ=', 'Friedrich', 'Meier', 'friedrich.meier@example.com', '01511234568', TRUE, FALSE, NULL, NOW()),
    ('rescuer3', '/qY5SZWNDmrbe3NQRvyGgzC6ux4wg4BV2lZMSb4T4wA=', 'Wolfgang', 'Koch', 'wolfgang.koch@example.com', '01511234569', TRUE, FALSE, NULL, NOW()),
    -- Meldende Benutzer (Notifiers)
    ('notifier1', '9RPLUi9SzekXRbUl45uP4apJj8Ei7SnLhojbMIfwrWc=', 'Sophie', 'Fischer', 'sophie.fischer@example.com', '01601234567', FALSE, FALSE, NULL, NOW()),
    ('notifier2', '4ImEkXaRuTuy4bxRWaOTz/ftqlZ+jaQTNJb3x3CKcD0=', 'Anna', 'Weber', 'anna.weber@example.com', '01601234568', FALSE, FALSE, NULL, NOW()),
    ('notifier3', 'KHDt5+SQOzncmlndsPoAhwOu/ia+3ZOHUnKzplBb2X0=', 'Klara', 'Bauer', 'klara.bauer@example.com', '01601234569', FALSE, FALSE, NULL, NOW());


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
