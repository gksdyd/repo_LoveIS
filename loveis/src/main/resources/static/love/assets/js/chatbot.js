const msgerForm = get(".msger-inputarea");
const msgerInput = get(".msger-input");
const msgerChat = get(".msger-chat");

const BOT_MSGS = [
  "Hi, how are you?",
  "Ohh... I can't understand what you trying to say. Sorry!",
  "I like to play games... But I don't know how to play!",
  "Sorry if my answers are not relevant. :))",
  "I feel sleepy! :("
];

// Icons made by Freepik from www.flaticon.com
const BOT_IMG = "/love/assets/images/avtar4.png";
const BOT_NAME = "BOT";
const PERSON_NAME = "Sajad";

msgerForm.addEventListener("submit", event => {
  event.preventDefault();

  const msgText = msgerInput.value;
  if (!msgText) return;

  appendMessage(PERSON_NAME, PERSON_IMG, "right", msgText);
  responAi(msgText);
  msgerInput.value = "";
});

function appendMessage(name, img, side, text) {
  //   Simple solution for small apps
  const msgHTML = `
    <div class="msg ${side}-msg">
          <div class="msg-info-time">${formatDate(new Date())}</div>
          <div class="msg-img">
				<img src="${img}" alt="avtar" class="img-fluid" style="border-radius: 50%; width: 80px; height: 70px;">
          </div>

      <div class="msg-bubble">
        <div class="msg-text">${text}</div>
      </div>
    </div>
  `;
//   const msgHTML = `
//     <div class="msg ${side}-msg">
//       <div class="msg-img" style="background-image: url(${img})"></div>

//       <div class="msg-bubble">
//         <div class="msg-info">
//           <div class="msg-info-name">${name}</div>
//           <div class="msg-info-time">${formatDate(new Date())}</div>
//         </div>

//         <div class="msg-text">${text}</div>
//       </div>
//     </div>
//   `;

  msgerChat.insertAdjacentHTML("beforeend", msgHTML);
  msgerChat.scrollTop += 500;
}

function botResponse() {
  const r = random(0, BOT_MSGS.length - 1);
  const msgText = BOT_MSGS[r];
  const delay = msgText.split(" ").length * 100;

  setTimeout(() => {
    appendMessage(BOT_NAME, BOT_IMG, "left", msgText);
  }, delay);
}

// Utils
function get(selector, root = document) {
  return root.querySelector(selector);
}

function formatDate(date) {
  const h = "0" + date.getHours();
  const m = "0" + date.getMinutes();

  return `${h.slice(-2)}:${m.slice(-2)}`;
}

function random(min, max) {
  return Math.floor(Math.random() * (max - min) + min);
}

function responAi(req) {
	messages.push(req);
  times.push(formatDate(new Date()));

  const contents = messages.map((message, index) => ({
    role: index % 2 === 0 ? "user" : "model",
    parts: [{ "text" : message }]
  }));

  const requestBody = { contents };

  fetch("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyDXzY2Yn3hdnRGV9cz7yBCVE4vPk5eJ7FI", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify(requestBody)
  })
  .then(res => res.json())
  .then(data => {
    let respon = data.candidates[0].content.parts[0].text;
		messages.push(respon);
    times.push(formatDate(new Date()));
    appendMessage(BOT_NAME, BOT_IMG, "left", respon);
  });
}

function appendMessage2(name, img, side, text, time) {
  //   Simple solution for small apps
  const msgHTML = `
    <div class="msg ${side}-msg">
          <div class="msg-info-time">${time}</div>
          <div class="msg-img">
				<img src="${img}" alt="avtar" class="img-fluid" style="border-radius: 50%; width: 80px; height: 70px;">
          </div>

      <div class="msg-bubble">
        <div class="msg-text">${text}</div>
      </div>
    </div>
  `;

  msgerChat.insertAdjacentHTML("beforeend", msgHTML);
  msgerChat.scrollTop += 500;
}