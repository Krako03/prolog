puedeEnviar(Persona,Amigo):- persona(Persona,_,Lista,_,_),miembro(Amigo,Lista).
miembro(E, [E|_]):- !.
miembro(E, [_|T]):- miembro(E,T).


amigoEnComun(Persona,Amigo,AmigosComun):-
    persona(Persona,_,LPersona,_,_),
    persona(Amigo,_,LAmigo,_,_),
    encontrarComun(LPersona,LAmigo,AmigosComun).

encontrarComun([],_,[]).
encontrarComun([H1|T1],LAmigos,[H1|Comunes]):-
    miembro(H1,LAmigos),
    encontrarComun(T1,LAmigos,Comunes).

encontrarComun([_|T1],LAmigos,Comunes):-
    encontrarComun(T1,LAmigos,Comunes).




persona(yael, 123, [saul_bejar,oscar],si_a_huevo,patito).
persona(saul_bejar, 1, [yael,oscar],_,arbol).
persona(yael_nuevo, 1, [],_,default).
