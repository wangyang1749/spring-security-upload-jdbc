console.log("0000")

function colorValue(min) {
    return Math.floor(Math.random() * 255 + min);
}
function createColorStyle(r, g, b) {
    return 'rgba(' + r + ',' + g + ',' + b + ', 0.8)';
}
function Color(min) {
    min = min || 0;
    this.r = colorValue(min);
    this.g = colorValue(min);
    this.b = colorValue(min);
    this.style = createColorStyle(this.r, this.g, this.b);
}
canvasControler = [
    {
        canvasEffect: function (canvas) {
            var dots = {
                nb: 1000,//Dot的总数
                distance: 50,
                d_radius: 100,
                array: []
              };
        
              //创建Dot类以及一系列方法
              function Dot() {
                this.x = Math.random() * canvas.width;
                this.y = Math.random() * canvas.height;
        
                this.vx = -.5 + Math.random();
                this.vy = -.5 + Math.random();
        
                this.radius = Math.random() * 2;
        
                this.color = new Color();
              }
        
              Dot.prototype = {
                draw: function () {
                  ctx.beginPath();
                  ctx.fillStyle = this.color.style;
                  ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
                  ctx.fill();
                }
              };
              for (var i = 0; i < dots.nb; i++) {
                dots.array.push(new Dot());
              }
        
              var ctx = canvas.getContext("2d");
              function animateDots() {
                ctx.clearRect(0, 0, canvas.width, canvas.height);//清除画布，否则线条会连在一起
                for (var i = 0; i < dots.nb; i++) {
                  var dot = dots.array[i];
                  if (dot.y < 0 || dot.y > canvas.height) {
                    // dot.vx = dot.vx;
                    dot.vy = - dot.vy;
                  }
                  else if (dot.x < 0 || dot.x > canvas.width) {
                    dot.vx = - dot.vx;
                    // dot.vy = dot.vy;
                  }
                  dot.x += dot.vx;
                  dot.y += dot.vy;
                }
                for (i = 0; i < dots.nb; i++) {
                  dots.array[i].draw();
                }
                requestAnimationFrame(animateDots)
              }
              animateDots()
        }
    }, {
        canvasEffect: function (canvas) {
            var ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.font = "30px Verdana";
            var gradient = ctx.createLinearGradient(50, 0, canvas.width, 50);
            gradient.addColorStop("0", "magenta");
            gradient.addColorStop("0.5", "blue");
            gradient.addColorStop("1.0", "red");
            // Fill with gradient
            ctx.strokeStyle = gradient;
            ctx.strokeText("Big smile!", 10, 60);
        }
    }
]
