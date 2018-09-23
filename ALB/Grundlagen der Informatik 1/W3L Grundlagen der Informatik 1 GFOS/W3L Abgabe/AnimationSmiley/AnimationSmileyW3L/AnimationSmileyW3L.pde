void setup()
{
  size(600,600);
  //Punkteraster
  for(int x=10;x<300;x=x+10)
    for(int y=10;y<300;y=y+10)
      point(x,y);
  frameRate(10);
  smooth();
  stroke(0,0,0);
}
int i=0;
int j=1;
//Animationswerte
float faktor = 1;
//faktor = 0.5;
//Auge
float linkesAugeX = 200;
float rechtesAugeX = 400;
float linkesAugeY = 260;
float rechtesAugeY = linkesAugeY;
//Brauen
float lBraueX = 200;
float lBraueY = 230;
float rBraueX = 400;
float rBraueY = lBraueY;
//Reflektionen
float reflektionXL1 = linkesAugeX-10;
float reflektionXL2 = linkesAugeX+5;
float reflektionYL1 = linkesAugeY-10;
float reflektionYL2 = linkesAugeY+26;
float reflektionXR1 = rechtesAugeX-10;
float reflektionXR2 = rechtesAugeX+5;
float reflektionYR1 = rechtesAugeY-10;
float reflektionYR2 = rechtesAugeY+26;
//Mund
float mund[] = {300,300,300,300,0.25*PI,0.52*PI};
//Mundwinkel
float mundWinkelX = 300;
float mundWinkelY = 450;
boolean pressed=false;
//Farben der Backen
float farbe[] = {254,143,27,110};

void draw()
{
  //Bildschirm aufraeumen
  fill(255,255,255,255);
  rect(0,0,width,height);
  //Klick-Button
  fill(255,0,0);
  rect(0,0,80,40);
  fill(0,0,0);
  text("Klick",16,25);
  textSize(20);
  stroke(0,0,0);
  
  //Punkteraster
  //for(int x=10;x<width;x=x+10)
  //  for(int y=10;y<height;y=y+10)
  //    point(x,y);
  
  //Gesicht
  stroke(199,95,41);
  fill(251,218,115);
  strokeWeight(4);
  ellipse(300,300,500,500);
  
  //Mund
  arc(mund[0],mund[1],mund[2],mund[3],mund[4],mund[5]);
  noFill();
  arc(mundWinkelX,mundWinkelY,20,20,0.8*PI,1.3*PI);
  arc(mundWinkelX+105,mundWinkelY-35,20,20,1.4*PI,1.9*PI);
  
  //Augen
  fill(255,255,255);
  ellipse(200,250,110,150);
  ellipse(400,250,110,150);
  
  //Pupillen
  //links
  noStroke();
  fill(92,111,181);
  ellipse(linkesAugeX,linkesAugeY,50,65); 
  fill(0,0,0);
  ellipse(linkesAugeX,linkesAugeY+6,30,35); //blau
  fill(255,255,255);
  //Reflektionen
  ellipse(reflektionXL1,reflektionYL1,18,18);
  ellipse(reflektionXL2,reflektionYL2,13,13);
  //rechts
  noStroke();
  fill(92,111,181);
  ellipse(rechtesAugeX,rechtesAugeY,50,65);
  fill(0,0,0);
  ellipse(rechtesAugeX,rechtesAugeY+6,30,35);
  fill(255,255,255);
  //Reflektionen
  ellipse(reflektionXR1,reflektionYR1,18,18);
  ellipse(reflektionXR2,reflektionYR2,13,13);
  
  //Augenbrauen
  noFill();
  stroke(199,95,41);
  arc(lBraueX,lBraueY,110,150,1.30*PI,1.70*PI);
  arc(rBraueX,rBraueY,110,150,1.30*PI,1.70*PI);
  
  //Backen
  noStroke();
  fill(farbe[0],farbe[1],farbe[2],farbe[3]);
  ellipse(110,360,40,60);
  ellipse(490,360,40,60);
  
  if(pressed==false) //umschalten durck "Klick"-Button
  {
      if(j<8)
      { 
        //Augen
        linkesAugeX += faktor;
        rechtesAugeX += faktor;
        //Brauen
        lBraueY -= faktor/1.5;
        rBraueY -= faktor/10;
        //Mundwinkel
        mundWinkelY += faktor/20;
        //Reflektionen
        reflektionXL1 = linkesAugeX-10-faktor/3;
        reflektionXL2 = linkesAugeX+5-faktor/3;
        reflektionXR1 = rechtesAugeX-10-faktor/2;
        reflektionXR2 = rechtesAugeX+5-faktor/2;
        reflektionYR1 = rechtesAugeY-10+faktor/2;
        reflektionYR2 = rechtesAugeY+26+faktor/2;
        reflektionYL1 = linkesAugeY-10+faktor/3;
        reflektionYL2 = linkesAugeY+26+faktor/3;
        //Backenfarbe
        farbe[3] -= faktor*8;
        //Mund
        mund[2] += faktor;
        mund[3] += faktor;
        mund[4] = (0.25-0.0002*j)*PI;
        mund[5] = (0.52-0.0002*j)*PI;
        
      }
      if(j>=8)
      {
        //Augen
        linkesAugeX -= faktor;
        rechtesAugeX -= faktor;
        //Brauen
        lBraueY += faktor/1.5;
        rBraueY += faktor/10;
        //Mundwinkel
        mundWinkelY -= faktor/20;
        //Reflektionen
        reflektionXL1 = linkesAugeX-10+faktor/3;
        reflektionXL2 = linkesAugeX+5+faktor/3;
        reflektionXR1 = rechtesAugeX-10+faktor/2;
        reflektionXR2 = rechtesAugeX+5+faktor/2;
        reflektionYR1 = rechtesAugeY-10-faktor/2;
        reflektionYR2 = rechtesAugeY+26-faktor/2; 
        reflektionYL1 = linkesAugeY-10-faktor/3;
        reflektionYL2 = linkesAugeY+26-faktor/3;
        //Backenfarbe
        farbe[3] += faktor*8;
        //Mund
        mund[2] -= faktor;
        mund[3] -= faktor;
        mund[4] = (0.25+0.0002*j)*PI;
        mund[5] = (0.52+0.0002*j)*PI;
        if(mousePressed)
          if(mouseX > 0 && mouseX < 80 && mouseY > 0 && mouseY < 40)
            pressed = true;
      }
  }
  else
  {
    if(mousePressed)
      pressed = false;
  }
  //j zuruecksetzen
  if(j==14)
    j=0;
  i++;
  j++;
}