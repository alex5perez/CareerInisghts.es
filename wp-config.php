<?php
define( 'WP_CACHE', true );
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * Localized language
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'u133604595_SPyt2' );

/** Database username */
define( 'DB_USER', 'u133604595_f2VaX' );

/** Database password */
define( 'DB_PASSWORD', '0l6p7KeVxg' );

/** Database hostname */
define( 'DB_HOST', '127.0.0.1' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',          '0XUL&@e6G`3j.ui::q~OXe-P/q-lUxD_0vf9XTC+|8tuyUr1_:~;v.;u#.u$?=4X' );
define( 'SECURE_AUTH_KEY',   '%#TN+qHS>sCpwMbp#Plwreui?mni$Hbx=u?V{$o _hAT4h}0+G,KFT]:ts#K;L|P' );
define( 'LOGGED_IN_KEY',     '12?D]3,!>N#-+5]U7|vK%6(bByH{ja2z=*HJ%nT9d%m-cnH?$Rh_Ue-K&sCpyv`T' );
define( 'NONCE_KEY',         '472JRhFXO/d_ZWcou)A,4{isF*#lXA*e-X:>9-csgHF{4k(4H(5w:T~y<en-QZ}`' );
define( 'AUTH_SALT',         'hxb4RKNWZJ}4pq8Vl(Fg_*:rQ99-Nzjtb:!i/EnPbOqGhF%zXkaC8|+aLH|Z~}0>' );
define( 'SECURE_AUTH_SALT',  '$O+=c-6^QUC~pitBI*3B {|#1|Q5Gxv1-JLl7OfU=c60N*cVIIq=!acV{j<qJ3pH' );
define( 'LOGGED_IN_SALT',    'UC{wCvI=Z9K#,#]qv.20BqSimQTpp<C.V h+uxn|(yM-D]V{Y<KD$vo_=[yN%zF]' );
define( 'NONCE_SALT',        'a-5|FizRqvP@yLQj~nXB};8OT,Ul  ]0pEx$JR&~$2xQwp29d$X4}<qJc%esmQnq' );
define( 'WP_CACHE_KEY_SALT', '*4aH}:Tp(Q8QaNe=+<M)_,s6i`7Lu:jxI*K1>4CxJEej&:Y@(D[pOGrpaO||bK10' );


/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';


/* Add any custom values between this line and the "stop editing" line. */



/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
if ( ! defined( 'WP_DEBUG' ) ) {
	define( 'WP_DEBUG', false );
}

define( 'FS_METHOD', 'direct' );
define( 'COOKIEHASH', '227eb8fe19de0077c89c1579ce3f65fa' );
define( 'WP_AUTO_UPDATE_CORE', 'minor' );
/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
