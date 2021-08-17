use oop_project;

CREATE TABLE UserInfos(
    ID                INT NOT NULL AUTO_INCREMENT,
    first_name        VARCHAR(50) NOT NULL,
    last_name         VARCHAR(70) NOT NULL,
    email             VARCHAR(100) NOT NULL,
    address           VARCHAR(150), -- we'll make mandatory fields NOT NULL for safety
    phone_number      VARCHAR(15) NOT NULL,
    note              VARCHAR(150),
    PRIMARY KEY (ID)
);

CREATE TABLE Users(
    ID                INT NOT NULL AUTO_INCREMENT,
    user_info_ID      INT NOT NULL,
    user_name         VARCHAR(100) BINARY NOT NULL UNIQUE,
    password          VARCHAR(80) NOT NULL, -- 100 because we will save SHA-256 HASHes (they are 64-char long)
    is_dealer         BOOLEAN NOT NULL DEFAULT FALSE,
    is_admin          BOOLEAN NOT NULL DEFAULT FALSE,
    is_banned         BOOLEAN NOT NULL DEFAULT FALSE,
    auctions_won      INT NOT NULL DEFAULT 0,
    rating            DOUBLE PRECISION NOT NULL DEFAULT 0,
    num_reviews       INT NOT NULL DEFAULT 0,
    PRIMARY KEY (ID),
    FOREIGN KEY (user_info_ID) REFERENCES UserInfos(ID)
);

CREATE TABLE Messages(
    ID                INT NOT NULL AUTO_INCREMENT,
    from_user_ID      INT NOT NULL,
    to_user_ID        INT NOT NULL,
    message           VARCHAR(500) NOT NULL, -- every message will have a character limit of 500
    time_sent         TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (from_user_ID) REFERENCES Users(ID),
    FOREIGN KEY (to_user_ID) REFERENCES Users(ID)
);

CREATE TABLE User_Followers(
    ID                INT NOT NULL AUTO_INCREMENT,
    follower_ID       INT NOT NULL,
    followee_ID       INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (follower_ID) REFERENCES Users(ID),
    FOREIGN KEY (followee_ID) REFERENCES Users(ID)
);

CREATE TABLE Reviews(
    ID                INT NOT NULL AUTO_INCREMENT,
    reviewer_ID       INT NOT NULL,
    recipient_ID      INT NOT NULL,
    score             INT NOT NULL,
    review            VARCHAR(500), --  reviews will have a character limit of 500 (can be empty)
    PRIMARY KEY (ID),
    FOREIGN KEY (reviewer_ID) REFERENCES Users(ID),
    FOREIGN KEY (recipient_ID) REFERENCES Users(ID)
);

CREATE TABLE Auctions(
    ID                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    seller_ID     	INT NOT NULL,
    current_bidder_ID INT NOT NULL,
    starting_price	INT UNSIGNED NOT NULL,
    min_increment		INT UNSIGNED NOT NULL,
    end_time			timestamp NULL DEFAULT NULL,
    current_price		INT UNSIGNED NOT NULL ,
    item_name       VARCHAR(50) NOT NULL,
    item_description       VARCHAR(500) NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES Users(ID),
    FOREIGN KEY (current_bidder_ID) REFERENCES Users(ID)
);


CREATE TABLE Bidder_Auctions(
    ID                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bidder_ID     	INT NOT NULL,
    auction_ID        INT NOT NULL,
    bid				INT UNSIGNED NOT NULL,
    FOREIGN KEY (bidder_ID) REFERENCES Users(ID),
    FOREIGN KEY (auction_ID) REFERENCES Auctions(ID)
);
