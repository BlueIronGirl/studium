void setup()
{
  //Größe der Zeichenfläche
  size(300, 300);

  //Gitterraster
  for(int x=10; x<300; x=x+10)
   for(int y=10; y<300; y=y+10)
     point (x, y);
  frameRate(20);
  smooth();
}
int rot1, rot2, rot3;
int gelb1, gelb2,gelb3;
int gruen1, gruen2, gruen3;
int zustand = 0;

void draw()
{
  fill(0);
  rect(120,70,60,180);
  fill(rot1,rot2, rot3);
  ellipse(150,100,50,50);
  fill(gelb1,gelb2,gelb3); //grün
  ellipse(150,155,50,50);
  fill(gruen1,gruen2,gruen3); //grün
  ellipse(150,210,50,50);
  switch (zustand)
  {
   case 0: //Rotphase
    rot1 = 255; rot2 = 0; rot3 = 0;
    gelb1 = 118; gelb2 = 92; gelb3 = 6;
    gruen1 = 1; gruen2 = 93; gruen3 = 0;
    break;
   
   case 1: //Gelbphase
    rot1 = 116; rot2 = 1; rot3 = 5;
    gelb1 = 255; gelb2 = 255; gelb3 = 0;
    gruen1 = 1; gruen2 = 93; gruen3 = 0;
    break;
    
   case 2: //Grünphase
     rot1 = 116; rot2 = 1; rot3 = 5;
     gelb1 = 118; gelb2 = 92; gelb3 = 6;
     gruen1 = 0; gruen2 = 255; gruen3 = 0;
     break;
  }
}

void mouseClicked() 
{
  zustand = (zustand + 1) % 3;
}
