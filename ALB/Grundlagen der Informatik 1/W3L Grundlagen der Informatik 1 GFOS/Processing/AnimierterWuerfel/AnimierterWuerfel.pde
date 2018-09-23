void setup()
{
  size(300,300,P3D);
  frameRate(20);
}

void draw()
{
  background(255,204,0); //gelb
  lights();
  translate(width/2, height/2);
  rotateY(frameCount*PI/60);
  rotateX(frameCount*PI/40);
  fill(0,0,255); //blau
  strokeWeight(5);
  box(120,120,120);
  translate(58,48,0);
  sphere(28);
  translate(70,60,0);
  sphere(30);
  translate(100,180,0);
  sphere(40);
  
} 
