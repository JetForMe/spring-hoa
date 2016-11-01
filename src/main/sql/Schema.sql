/*
	Set default character set to utf8.
	Set default storage engine to InnoDB.
*/

create table if not exists User
(
    mId                     integer             auto_increment  primary key     not null,
    mUsername               varchar(255)                        unique          not null,
    mLogin                  varchar(255)                        unique          not null,
    mPassword               varchar(255),

    mEmail                  varchar(255),
    mFirst                  varchar(255),
    mNick                   varchar(255),
    mLast                   varchar(255),
    mCreateDate             datetime                                            not null,
    mLastLoginDate          datetime,

    mPhone                  varchar(255),
    mMobilPhone             varchar(255)
);

/**
    @param  mUnitNumber     Apartment, Suite, etc.
*/

create table if not exists Address
(
    mId                     integer             auto_increment  primary key     not null,
    mStreetNumber           integer,
    mStreet1                varchar(255),
    mStreet2                varchar(255),
    mUnitNumber             varchar(16),
    mCity                   varchar(255),
    mState                  varchar(255),
    mCounty                 varchar(255),
    mCountry                varchar(64)

    mEditOf                 integer,

    constraint cAddressEditOf   foreign key (mEditOf) references Address (mId),
);

/**
    mMainAddressId          The main, physical address for the HOA.
*/

create table if not exists HOA
(
    mId                     integer             auto_increment  primary key     not null,
    mUsername               varchar(255)                                        not null,
    mMainAddressId          integer                                             not null,

    constraint cMainddress      foreign key (mMainAddressId) references Address (mId)
);

/**
    @param  mType           Unit type:
                                
                                0 - Unknown
                                1 - Single-family home
                                2 - Town Home
                                3 - Condo
                                4 - Apartment
                                5 - Mobile Home
                                6 - Hotel Room
                                99 - Other
*/

create table if not exists Unit
(
    mId                     integer             auto_increment  primary key     not null,
    mAddressId              integer                                             not null,
    mType                   integer                                             default 0,

    constraint cUnitAddress     foreign key (mAddressId) references Address (mId)
);
