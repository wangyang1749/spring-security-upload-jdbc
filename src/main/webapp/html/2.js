console.log("0000")

canvasControler = [
  {
    canvasEffect: function (canvas) {
      var ctx = canvas.getContext("2d");
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      var gradient = ctx.createLinearGradient(0, 0, 170, 0);
      gradient.addColorStop("0", "magenta");
      gradient.addColorStop("0.5", "blue");
      gradient.addColorStop("1.0", "red");

      // Fill with gradient
      ctx.strokeStyle = gradient;
      ctx.lineWidth = 5;
      ctx.strokeRect(100, 80, 150, 100);
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
