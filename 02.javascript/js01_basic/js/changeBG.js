function random(number) {
  // Math.random() -> 0 <= random < 1 사이의 실수 1개를 리턴
  // Math.floor(매개변수(실수)) -> 전달받은 실수에 가장 낮은 정수를 리턴
  return Math.floor(Math.random() * number)
}

function bgColorChange() {
  // r = random(255)
  // b = random(255)
  // g = random(255)
  // rndCol = 'rgb(r, b, g)'
  // let r = random(255);
  // let g = random(255);
  // let b = random(255);
  // rndCol = `rgb(${r}, ${g}, ${b})`;
  // document.body.style.backgroundColor = rndCol; 

  document.body.style.backgroundColor
   = `rgb(${random(255)},${random(255)},${random(255)})`; 

}
