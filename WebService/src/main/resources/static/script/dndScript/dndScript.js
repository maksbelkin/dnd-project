let currentAngle = 0;
let currentAngleWorld = 0;

let imgLock = document.querySelector('.imgLock');
let imgLockBottom = document.querySelector('.imgLockBottom');
let imgForge = document.querySelector('.imgForge');
let imgWorld = document.querySelector('.imgWorld');

let imgForgePic = imgForge.querySelector('img');
let imgWorldPic = imgWorld.querySelector('img');

let buttonImgForge = document.querySelector('.buttonImgForge');
let buttonImgWorld = document.querySelector('.buttonImgWorld');

let flagButton = false;
let flagButtonWorld = false;
let time = 0;
let timeWorld = 0;

buttonImgForge.addEventListener('click', function (e) {
    e.preventDefault();
    flagButton = true;
});

let intervalId = setInterval(() => {
    if (imgLock && flagButton) {
        currentAngle -= 10;
        imgLock.style.transform = `translate(-50%, -50%) rotate(${currentAngle}deg)`;

        imgWorldPic.style.transition = 'filter 5s ease';
        imgWorldPic.style.filter = 'brightness(0.55) sepia(1) saturate(3) hue-rotate(-18deg)';

        if (currentAngle == -80) {
            flagButton = false;
            let intervalIdTwo = setInterval(() => {
                time += 1;
                if (time == 2) {
                    flagButton = true;
                    clearInterval(intervalIdTwo);
                }
            }, 800);
        } else if (currentAngle == -180) {
            flagButton = false;
            clearInterval(intervalId);

            let intervalIdThree = setInterval(() => {
                time += 1;
                if (time == 3) {
                    imgForge.style.transition = 'transform 0.5s ease, opacity 0.5s ease';
                    imgForge.style.transform = 'translateX(-100px)';
                    imgForge.style.opacity = '0';

                    imgWorld.style.transition = 'transform 0.5s ease, opacity 0.5s ease';
                    imgWorld.style.transform = 'translateX(100px)';
                    imgWorld.style.opacity = '0';

                    setTimeout(() => {
                        window.location.href = '/authorization';
                    }, 500);

                    clearInterval(intervalIdThree);
                }
            }, 1500);
        }
    }
}, 100);

buttonImgWorld.addEventListener('click', function (e) {
    e.preventDefault();
    flagButtonWorld = true;
});

let intervalIdWorld = setInterval(() => {
    if (imgLockBottom && flagButtonWorld) {
        currentAngleWorld += 10;
        imgLockBottom.style.transform = `translate(-50%, -50%) rotate(${currentAngleWorld}deg)`;

        imgForgePic.style.transition = 'filter 5s ease';
        imgForgePic.style.filter = 'brightness(0.55) sepia(1) saturate(3) hue-rotate(-18deg)';

        if (currentAngleWorld == 80) {
            flagButtonWorld = false;
            let intervalIdWorldTwo = setInterval(() => {
                timeWorld += 1;
                if (timeWorld == 2) {
                    flagButtonWorld = true;
                    clearInterval(intervalIdWorldTwo);
                }
            }, 800);
        } else if (currentAngleWorld == 180) {
            flagButtonWorld = false;
            clearInterval(intervalIdWorld);

            let intervalIdWorldThree = setInterval(() => {
                timeWorld += 1;
                if (timeWorld == 3) {
                    imgForge.style.transition = 'transform 0.5s ease, opacity 0.5s ease';
                    imgForge.style.transform = 'translateX(-100px)';
                    imgForge.style.opacity = '0';

                    imgWorld.style.transition = 'transform 0.5s ease, opacity 0.5s ease';
                    imgWorld.style.transform = 'translateX(100px)';
                    imgWorld.style.opacity = '0';

                    setTimeout(() => {
                        window.location.href = '/registration';
                    }, 500);

                    clearInterval(intervalIdWorldThree);
                }
            }, 1500);
        }
    }
}, 100);