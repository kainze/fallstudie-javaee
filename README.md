# fallstudie-javaee
Fallstudie Programmierung von industriellen Informationssystemen mit Java EE Aufgabenstellung 3: Ghost Net Fishing


1. MUST: Als meldende Person möchte ich Geisternetze (anonym) erfassen können.
-> index.xhtml Geisternetze können auch ohne Login erfasst werden.



3. MUST: Als bergende Person möchte ich sehen, welche Geisternetze noch zu bergen sind.
-> myGhostNet.xhtml (offene Netze Table) Liste aller Tabellen mit Status Gemeldet

2. MUST: Als bergende Person will ich mich für die Bergung eines Geisternetzes eintragen können.
-> myGhostNet.xhtml (offene Netze Table) nur wenn Bergende Person true

4. MUST: Als bergende Person möchte ich Geisternetze als geborgen melden können.
-> myGhostNet.xhtml (meine Netze Table) hier habe ich alle Netzte für die ich mich als Rescuer eingetragen habe, und kann diese als geborgen melden

6. COULD: Als bergende Person möchte ich sehen können, wer welche Geisternetze bergen möchte, um sich
ggf. abzustimmen und die Bergungen umzuverteilen.
-> myGhostNet.xhtml (Netze anderer) inkl. Button Request Assignment aber diese Funktion dahinter noch nicht implementiert
TODO show rescuer

5. COULD: Als bergende Person möchte ich die noch nicht geborgenen Netze auf einer Weltkarte sehen.
-> worldmap.xhtml worldMap, auf der man alle Netze sehen kann

7. COULD: Als beliebige Person möchte ich Geisternetze als verschollen melden können
-> GhostNet.xhtml
Todo in der GhostNet.xhtml