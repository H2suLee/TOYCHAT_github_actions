// /public/firebase-messaging-sw.js
importScripts(
  "https://www.gstatic.com/firebasejs/10.8.0/firebase-app-compat.js"
);
importScripts(
  "https://www.gstatic.com/firebasejs/10.8.0/firebase-messaging-compat.js"
);

self.addEventListener("install", function (e) {
  console.log("fcm service worker가 설치되었습니다.");
  self.skipWaiting();
});

self.addEventListener("activate", function (e) {
  console.log("fcm service worker가 실행되었습니다.");
});

const firebaseConfig = {
  apiKey: "AIzaSyBUochlrZu3cwXnaW907_ZvHPPNQkCHUaE",
  authDomain: "toychat-1a2b7.firebaseapp.com",
  projectId: "toychat-1a2b7",
  storageBucket: "toychat-1a2b7.firebasestorage.app",
  messagingSenderId: "286624352269",
  appId: "1:286624352269:web:c05c71880d215786ea2d44",
  measurementId: "G-8LWB2ZRCW1",
};

// 파이어베이스 초기화
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();
console.log("onBackgroundMessage ");
messaging.onBackgroundMessage((payload) => {
  console.log("onBackgroundMessage : ", payload);
  const notificationTitle = payload.data.title;
  const notificationOptions = {
    body: payload.data.body,
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
});
