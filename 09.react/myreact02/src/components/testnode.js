function time() {
  var date = new Date();
  var hour = date.getHours();
  var min = date.getMinutes();
  var sec = date.getSeconds();
  if (hour < 10) hour = "0" + hour;
  if (min < 10) min = "0" + min;
  if (sec < 10) sec = "0" + sec;
  return hour + ":" + min + ":" + sec;
}

function wait(sec) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve();
    }, sec * 1000);
  });
}

console.log("시작", time());
wait(2)
  .then(() => {
    console.log("2초뒤", time());
    return wait(1);
  })
  .then(() => {
    console.log("3초뒤", time());
    return wait(1);
  })
  .then(() => {
    console.log("4초뒤", time());
  });
