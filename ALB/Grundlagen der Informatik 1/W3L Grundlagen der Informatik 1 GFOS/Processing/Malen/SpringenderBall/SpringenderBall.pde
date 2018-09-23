int x = 0, y = 0, radius = 0;
float daempfung = 0.0;
float gravitation = 0.0;
float reibung = 0.0;
float geschwX = 0.0, geschwY = 0.0;

void setup()
{
  size(400,400);
  x = width / 2;
  radius = 50;
  fill(0);
  geschwX = 4;
  gravitation = 0.5;
  daempfung = 0.8;
  reibung = 0.9;
  smooth();
}

void draw()
{
  //Bildschirm aufräumen
  fill(0,0,0,255);
  rect(0,0,width,height);
  fill(0,0,255,255);
  ellipse(x,y,radius,radius);
  x = x + int(geschwX);
  geschwY = geschwY + gravitation;
  y = y + int(geschwY);
  //Bildschirmränder prüfen
  if (x > width - (radius/2))
  {
    x = width - (radius / 2);
    geschwX = -geschwX;
  }
  else if (y > height - (radius / 2))
  {
    y = height - (radius /2);
    geschwY = -geschwY;
    geschwY = geschwY * daempfung;
    geschwX = geschwX * reibung;
  }
  else if (y < 0)
  {
    y = 0;
    geschwY = -geschwY;
  }
}
