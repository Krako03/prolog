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


diferencia_listas([], _, []).
diferencia_listas([X|RestoA], B, Diferencia) :-
    persona(X, _, _, _, _),  % Aseg�rate de que X es una persona
    member(X, B),
    !,
    diferencia_listas(RestoA, B, Diferencia).
diferencia_listas([X|RestoA], B, [X|Diferencia]) :-
    persona(X, _, _, _, _),  % Aseg�rate de que X es una persona
    diferencia_listas(RestoA, B, Diferencia).

personasNoAmigas(Persona, Resultado) :-
    persona(Persona, _, Amigos, _, _),
    mundo(Mundo),
    diferencia_listas(Mundo, [Persona|Amigos], Resultado).

mundo([yael,saul_bejar,oscar,isaac,montse,pablo,juan,noe,hipo]).

persona(yael, 123, [noe,pablo,oscar,saul_bejar],si,joao).
persona(saul_bejar, 1, [pablo,yael,oscar,isaac,montse,noe,hipo],dwadwa,default).

persona(oscar, 1, [],nel_pastel,joao).
persona(montse, 1, [],nel_pastel,default).
persona(isaac, 1, [],nel_pastel,joao).
persona(pablo, 1, [],nel_pastel,joao).
persona(hipo, 1, [],nel_pastel,joao).
persona(noe, 1, [],nel_pastel,joao).
persona(juan, 1, [],nel_pastel,joao).

