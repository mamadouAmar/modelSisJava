-- Creation des tables --

-- Table patient
CREATE TABLE IF NOT EXISTS patient(
    noss SERIAL PRIMARY KEY,
    nom text
);

-- Table medecin
CREATE TABLE IF NOT EXISTS medecin(
    matricule SERIAL PRIMARY KEY,
    nom text
);

-- Table medicament
CREATE TABLE IF NOT EXISTS medicament(
    code SERIAL PRIMARY KEY,
    libelle text
);

-- Table consultation
CREATE TABLE IF NOT EXISTS consultation(
    no_consultation SERIAL PRIMARY KEY,
    matricule INT REFERENCES medecin (matricule),
    noss INT REFERENCES patient (noss),
    date_consultation TIMESTAMP
);

-- Table prescription
CREATE TABLE IF NOT EXISTS prescription(
    no_prescription SERIAL,
    no_consultation INT REFERENCES consultation (no_consultation),
    code INT REFERENCES medicament (code),
    nbPrises INT NOT NULL CHECK (nbPrises > 0 AND nbPrises < 5)

);