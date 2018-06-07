/*
 * Functions SDK : is required to work with firebase functions.
 * Admin SDK : is required to send Notification using functions.
 */

'use strict'

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);


/*
 * 'OnWrite' works as 'addValueEventListener' for android. It will fire the function
 * everytime there is some item added, removed or changed from the provided 'database.ref'
 * 'sendNotification' is the name of the function, which can be changed according to
 * your requirement
 */

exports.sendNotification = functions.database.ref('/notifications/{user_id}/{notification_id}').onWrite(event => {


  /*
   * You can store values as variables from the 'database.ref'
   * Just like here, I've done for 'user_id' and 'notification'
   */

  const user_id = event.params.user_id;
  const notification_id = event.params.notification_id;

  console.log('We have a notification from : ', user_id);