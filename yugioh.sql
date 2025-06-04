-- Database: YUGIOH
CREATE DATABASE IF NOT EXISTS yugioh CHARACTER SET utf8mb4 COLLATE utf8mb4_latvian_ci;

-- Use the created database
USE yugioh;

-- Create table for card attributes
CREATE TABLE attributes (
    attribute_id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique ID for each attribute
    name VARCHAR(255) NOT NULL           -- Name of the attribute (e.g., Light, Dark, Fire)
);

-- Create table for monster races
CREATE TABLE races (
    race_id INT AUTO_INCREMENT PRIMARY KEY,       -- Unique ID for each race
    name VARCHAR(255) NOT NULL               -- Name of the race (e.g., Dragon, Warrior)
);

-- Create table for card images
CREATE TABLE card_images (
    image_id INT AUTO_INCREMENT PRIMARY KEY,      -- Unique ID for each image
    name VARCHAR(255) NOT NULL,               -- URL to the image
    image MEDIUMBLOB NOT NULL,
    content_type VARCHAR(255) NOT NULL
);

-- Create table for archetypes
CREATE TABLE archetypes (
    archetype_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each archetype
    name VARCHAR(255) NOT NULL          -- Name of the archetype (e.g., Blue-Eyes, Dark Magician)
);

-- Create table for frame types (e.g., Normal, Effect, Ritual)
CREATE TABLE frame_types (
    frame_type_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique ID for each frame type
    name VARCHAR(255) NOT NULL         -- Name of the frame type (e.g., Normal, Effect)
);

-- Create table for card types (Monster, Spell, Trap)
CREATE TABLE card_types (
    card_type_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each card type
    name VARCHAR(255) NOT NULL          -- Name of the card type (e.g., Monster, Spell, Trap)
);

-- Create table for rarities (Common, Rare, Ultra Rare)
CREATE TABLE rarities (
    rarity_id INT AUTO_INCREMENT PRIMARY KEY,     -- Unique ID for each rarity
    name VARCHAR(255) NOT NULL             -- Name of the rarity (e.g., Common, Rare)
);
-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,  -- For storing a hashed password
    name VARCHAR(255));

-- Create the table for sets
CREATE TABLE sets (
    set_id INT AUTO_INCREMENT PRIMARY KEY,      -- Unique ID for each set
    name VARCHAR(255) NOT NULL,              -- Name of the set (e.g., 'Legend of Blue Eyes White Dragon')
    code VARCHAR(50) NOT NULL,               -- Set code (e.g., 'LOB' for Legend of Blue Eyes)
    image_id INT,
    user_id INT,
    CONSTRAINT image_set_fk FOREIGN KEY (image_id) REFERENCES card_images(image_id) ON UPDATE CASCADE,
    CONSTRAINT user_set_fk FOREIGN KEY (user_id) REFERENCES users(user_id)
);
-- Create table to store card information with foreign keys to other tables
CREATE TABLE cards (
    card_id INT AUTO_INCREMENT PRIMARY KEY,       -- Unique ID for each card
    name VARCHAR(255) NOT NULL,                   -- Name of the card
    description MEDIUMTEXT,                     -- Description of the card
    attack INT,                                   -- Attack points (if applicable)
    defense INT,                                  -- Defense points (if applicable)
    level INT,                                    -- Level (for monsters)
    attribute_id INT,                             -- Foreign key to attributes table
    race_id INT,                                  -- Foreign key to races table
    archetype_id INT,                             -- Foreign key to archetypes table
    frame_type_id INT,                            -- Foreign key to frame types table
    card_type_id INT,                             -- Foreign key to card types table
    image_id INT,                                 -- Foreign key to card_images table
    user_id INT,
    CONSTRAINT attribute_fk FOREIGN KEY (attribute_id) REFERENCES attributes(attribute_id) ON UPDATE CASCADE,
    CONSTRAINT race_fk FOREIGN KEY (race_id) REFERENCES races(race_id) ON UPDATE CASCADE,
    CONSTRAINT archetype_fk FOREIGN KEY (archetype_id) REFERENCES archetypes(archetype_id) ON UPDATE CASCADE,
    CONSTRAINT frame_type_fk FOREIGN KEY (frame_type_id) REFERENCES frame_types(frame_type_id) ON UPDATE CASCADE,
    CONSTRAINT card_type_fk FOREIGN KEY (card_type_id) REFERENCES card_types(card_type_id) ON UPDATE CASCADE,
    CONSTRAINT image_fk FOREIGN KEY (image_id) REFERENCES card_images(image_id) ON UPDATE CASCADE,
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create table to link cards to sets (many-to-many relationship)
CREATE TABLE card_sets (
    card_set_id INT AUTO_INCREMENT PRIMARY KEY,
    card_id INT,                                 -- Foreign key to the cards table
    set_id INT,                                  -- Foreign key to the sets table
    rarity_id INT,
    CONSTRAINT card_sets_card_fk FOREIGN KEY (card_id) REFERENCES cards(card_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT card_sets_set_fk FOREIGN KEY (set_id) REFERENCES sets(set_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT card_sets_rarity_fk FOREIGN KEY (rarity_id) REFERENCES rarities(rarity_id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Decks Table
CREATE TABLE decks (
    deck_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id INT,  -- Foreign key linking to users
    CONSTRAINT user_deck_fk FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Deck Cards (Many-to-many relationship between Decks and Cards)
CREATE TABLE deck_cards (
    card_deck_id INT AUTO_INCREMENT PRIMARY KEY,
    deck_id INT,
    card_id INT,
    CONSTRAINT deck_card_fk FOREIGN KEY (deck_id) REFERENCES decks(deck_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT card_deck_fk FOREIGN KEY (card_id) REFERENCES cards(card_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO yugioh.rarities(name) VALUES ("Common");
INSERT INTO yugioh.rarities(name) VALUES ("Rare");
INSERT INTO yugioh.rarities(name) VALUES ("Super Rare");
INSERT INTO yugioh.rarities(name) VALUES ("Ultra Rare");
INSERT INTO yugioh.rarities(name) VALUES ("Secret Rare");

INSERT INTO yugioh.attributes(name) VALUES ("DARK");
INSERT INTO yugioh.attributes(name) VALUES ("DIVINE");
INSERT INTO yugioh.attributes(name) VALUES ("EARTH");
INSERT INTO yugioh.attributes(name) VALUES ("FIRE");
INSERT INTO yugioh.attributes(name) VALUES ("LIGHT");
INSERT INTO yugioh.attributes(name) VALUES ("WATER");
INSERT INTO yugioh.attributes(name) VALUES ("WIND");
INSERT INTO yugioh.attributes(name) VALUES ("LAUGH");