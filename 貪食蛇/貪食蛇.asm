TITLE Snake               


INCLUDE Irvine32.inc
main	EQU start@0
.DATA

a WORD 1920 DUP(0)  

tailR BYTE 13d        
tailC BYTE 55d         
headR BYTE 10d        
headC BYTE 55d         
foodR BYTE 0           
foodC BYTE 0           

tempR BYTE 0        
tempC BYTE 0         

rM BYTE 0d          
cM BYTE 0d          
rP BYTE 0d          
cP BYTE 0d          

delTail BYTE    1d  
search  WORD    0d  
eGame   BYTE    0d  
totalScore  DWORD   0d  ; Total score

direction       BYTE    'w' 
newDirection    BYTE    'w' 
delTime DWORD   50 ; Delay time between frames (game speed)

; Strings for menu display
menuS   BYTE "1. Start Game", 0Dh, 0Ah, "2. Select Level", 0Dh, 0Ah, "3. Exit",0Dh, 0Ah, 0
levelS  BYTE "1. Classic", 0Dh, 0Ah, "2. Guava", 0Dh, 0Ah, "3. LeeWeiChen", 0Dh, 0Ah, 0
hitS    BYTE "Game Over!", 0
scoreS  BYTE "Score: 0", 0

myHandle DWORD ?    
numInp   DWORD ?    
temp BYTE 16 DUP(?) 
bRead    DWORD ?    

.CODE

main PROC

    menu:
    CALL Randomize              
    CALL Clrscr                 
    MOV EDX, OFFSET menuS       
    CALL WriteString            
    waiting:                      
    CALL ReadChar

    CMP AL, '1'                 
    JE startG

    
    CMP AL, '2'                 
    JE level

    CMP AL, '3'                 
    JNE waiting                    
                                
    EXIT

    level:                      
    CALL Clrscr                 
    MOV EDX, OFFSET levelS      
    CALL WriteString            

    wait2:                     
    CALL ReadChar

    CMP AL, '1'                 
    JE level1

    CMP AL, '2'                 
    JE level2

    CMP AL, '3'                 
    JE level3

    JMP wait2                   

    level1:                     
    CALL clearMem               
    MOV AL, 1                   
    CALL GenLevel               
    JMP menu

    level2:                     
    CALL clearMem               
    MOV AL, 2                   
    CALL GenLevel               
    JMP menu

    level3:                     
    CALL clearMem               
    MOV AL, 3                   
    CALL GenLevel               
    JMP menu

    startG:                     
                                
    MOV EAX, 0                  
    MOV EDX, 0
    CALL Clrscr                 
    CALL initSnake              
    CALL Paint                  
    CALL createFood             
    CALL startGame              
    MOV EAX, white + (black * 16)
    CALL SetTextColor           
    JMP menu                    

main ENDP

initSnake PROC USES EBX EDX

    MOV DH, 10      
    MOV DL, 55      
    MOV BX, 1       
    CALL saveIndex  

    MOV DH, 11     
    MOV DL, 55      
    MOV BX, 2      
    CALL saveIndex  

    MOV DH, 12      
    MOV DL, 55      
    MOV BX, 3       
    CALL saveIndex  

    MOV DH, 13      
    MOV DL, 55      
    MOV BX, 4       
    CALL saveIndex  

    RET

initSnake ENDP

clearMem PROC


    MOV DH, 0               
    MOV BX, 0               

    oLoop:                  
        CMP DH, 24          
                           
        JE endOLoop

        MOV DL, 0           

        iLoop:              
            CMP DL, 80      
            JE endILoop     

            CALL saveIndex  
                            
            INC DL          
            JMP iLoop       

    endILoop:               
        INC DH              
        JMP oLoop           

endOLoop:                   
    MOV tailR, 13              
    MOV tailC, 55              
    MOV headR, 10              
    MOV headC, 55              

    MOV eGame, 0            
    MOV delTail, 1            
    MOV direction, 'w'              
    MOV newDirection, 'w'           
    MOV totalScore, 0           

    RET
clearMem ENDP

startGame PROC USES EAX EBX ECX EDX



        MOV EAX, white + (black * 16)       
        CALL SetTextColor
        MOV DH, 24                          
        MOV DL, 0                           
        CALL GotoXY                         
        MOV EDX, OFFSET scoreS
        CALL WriteString

        
        INVOKE getStdHandle, STD_INPUT_HANDLE
        MOV myHandle, EAX
        MOV ECX, 10

       
        INVOKE ReadConsoleInput, myHandle, ADDR temp, 1, ADDR bRead
        INVOKE ReadConsoleInput, myHandle, ADDR temp, 1, ADDR bRead

    more:

        ; Get number of events in input buffer
        INVOKE GetNumberOfConsoleInputEvents, myHandle, ADDR numInp
        MOV ECX, numInp

        CMP ECX, 0                          
        JE done                             

        ; Read one event from input buffer and save it at temp
        INVOKE ReadConsoleInput, myHandle, ADDR temp, 1, ADDR bRead
        MOV DX, WORD PTR temp               
        CMP DX, 1                           
        JNE SkipEvent                      

            MOV DL, BYTE PTR [temp+4]       
            CMP DL, 0
            JE SkipEvent
                MOV DL, BYTE PTR [temp+10]  

                CMP DL, 1Bh                 
                JE quit                    

                CMP direction, 'w'                  
                JE case1                    
                CMP direction, 's'                  
                JE case1                    

                JMP case2                   
                                            
                case1:
                    CMP DL, 25h             
                    JE case11
                    CMP DL, 27h             
                    JE case12
                    JMP SkipEvent           
                                          
                    case11:
                        MOV newDirection, 'a'       
                        JMP SkipEvent
                    case12:
                        MOV newDirection, 'd'       
                        JMP SkipEvent

                case2:
                    CMP DL, 26h             
                    JE case21
                    CMP DL, 28h             
                    JE case22
                    JMP SkipEvent          
                                            
                    case21:
                        MOV newDirection, 'w'       
                        JMP SkipEvent
                    case22:
                        MOV newDirection, 's'       
                        JMP SkipEvent

    SkipEvent:
        JMP more                            

    done:

        MOV BL, newDirection                       
                                            
        MOV direction, BL
        CALL MoveSnake                      
        MOV EAX, DelTime                    
        CALL Delay                          

        MOV BL, direction                          
        MOV newDirection, BL                       

        CMP eGame, 1                       
        JE quit                             

        JMP more                            

        quit:
        CALL clearMem                       
        MOV delTime, 50                   
                                            
    RET

startGame ENDP

MoveSnake PROC USES EBX EDX

    CMP delTail, 1            
    JNE NoETail            

        MOV DH, tailR          
        MOV DL, tailC         
        CALL accessIndex    
        DEC BX              
                            
        MOV search, BX      

        MOV BX, 0           ; Erase the value at current index from the
        CALL saveIndex      ; framebuffer (the snake tail)

        CALL GotoXY         ; Erase snake tail pixel from screen
        MOV EAX, lightRed + (black * 16)
        CALL SetTextColor
        MOV AL, ' '
        CALL WriteChar

        PUSH EDX            
        MOV DL, 79
        MOV DH, 23
        CALL GotoXY
        POP EDX

        MOV AL, DH          
        DEC AL              
        MOV rM, AL         
        ADD AL, 2           
        MOV rP, AL          

        MOV AL, DL          
        DEC AL              
        MOV cM, AL          
        ADD AL, 2           
        MOV cP, AL         

        CMP rP, 24          
        JNE next1
            MOV rP, 0       

        next1:
        CMP cP, 80          
        JNE next2
            MOV cP, 0       

        next2:
        CMP rM, 0           
        JGE next3
            MOV rM, 23      

        next3:
        CMP cM, 0          
        JGE next4
            MOV cM, 79      

        next4:

        MOV DH, rM          
        MOV DL, tailC          
        CALL accessIndex    
        CMP BX, search      
        JNE melseif1
            MOV tailR, DH      
            JMP mendif

        melseif1:
        MOV DH, rP          
        CALL accessIndex    
        CMP BX, search     
        JNE melseif2
            MOV tailR, DH     
            JMP mendif

        melseif2:
        MOV DH, tailR          
        MOV DL, cM          
        CALL accessIndex    
        CMP BX, search      
        JNE melse
            MOV tailC, DL      
            JMP mendif

        melse:
            MOV DL, cP      
            MOV tailC, DL

        mendif:

    NoETail:

    MOV delTail, 1            
    MOV DH, tailR              
    MOV DL, tailC              
    MOV tempR, DH            
    MOV tempC, DL            

    whileTrue:              
                            
        MOV DH, tempR       
        MOV DL, tempC        
        CALL accessIndex    
        DEC BX              
                            
        MOV search, BX      
        PUSH EBX            
        ADD BX, 2           
        CALL saveIndex      
        POP EBX

        CMP BX, 0           
        JE break            
        MOV AL, DH          
        DEC AL              
        MOV rM, AL          
        ADD AL, 2           
        MOV rP, AL          

        MOV AL, DL          
        DEC AL              
        MOV cM, AL          
        ADD AL, 2          
        MOV cP, AL          

        CMP rP, 24          
        JNE next21
            MOV rP, 0       

        next21:
        CMP cP, 80          
        JNE next22
            MOV cP, 0       

        next22:
        CMP rM, 0           
        JGE next23
            MOV rM, 23      

        next23:
        CMP cM, 0           
        JGE next24
            MOV cM, 79     

        next24:

        MOV DH, rM          ;
        MOV DL, tempC        
        CALL accessIndex    ; 
        CMP BX, search      ; 
        JNE elseif21
            MOV tempR, DH    ; 
            JMP endif2

        elseif21:
        MOV DH, rP          ;
        CALL accessIndex    
        CMP BX, search      
        JNE elseif22
            MOV tempR, DH    
            JMP endif2

        elseif22:
        MOV DH, tempR        
        MOV DL, cM          
        CALL accessIndex    
        CMP BX, search      
        JNE else2
            MOV tempC, DL    
            JMP endif2

        else2:
            MOV DL, cP      
            MOV tempC, DL

        endif2:
        JMP whileTrue       

    break:

    MOV AL, headR              
    DEC AL                  
    MOV rM, AL              
    ADD AL, 2               
    MOV rP, AL              

    MOV AL, headC              
    DEC AL                  ;
    MOV cM, AL              ; 
    ADD AL, 2               ; 
    MOV cP, AL              ; 

    CMP rP, 24              ; 
    JNE next31
        MOV rP, 0           ;

    next31:
    CMP cP, 80              ; 
    JNE next32
        MOV cP, 0           ; 

    next32:
    CMP rM, 0               ; 
    JGE next33
        MOV rM, 23          ; 

    next33:
    CMP cM, 0               ; 
    JGE next34
        MOV cM, 79          ; 

    next34:

    CMP direction, 'w'              ;
    JNE elseif3
        MOV AL, rM          ; 
        MOV headR, AL          ; 
        JMP endif3

    elseif3:
    CMP direction, 's'              
    JNE elseif32
        MOV AL, rP          
        MOV headR, AL        
        JMP endif3

    elseif32:
    CMP direction, 'a'              
    JNE else3
        MOV AL, cM          
        MOV headC, AL          
        JMP endif3

    else3:
        MOV AL, cP          
        MOV headC, AL          

    endif3:

    MOV DH, headR            
    MOV DL, headC              

    CALL accessIndex        ;
    CMP BX, 0               
    JE NoHit                
                            
    MOV EAX, 4000           ; 
    MOV DH, 24              ;
    MOV DL, 11              ; 
    CALL GotoXY
    MOV EDX, OFFSET hitS
    CALL WriteString

    CALL Delay              ;
    MOV eGame, 1            ;

    RET                     ; 

    NoHit:                  ; 
    MOV BX, 1               ; 
    CALL saveIndex          

    MOV cl, foodC              ; 
    MOV ch, foodR              ;

    CMP cl, DL              ;
    JNE foodNotGobbled      ; 
    CMP ch, DH              ; 
    JNE foodNotGobbled      ; 

    CALL createFood         ; 
    MOV delTail, 0            ; 
                            ; 

    MOV EAX, lightRed + (black * 16)
    CALL SetTextColor       ; 

    PUSH EDX                ; 

    MOV DH, 24              ; 
    MOV DL, 7
    CALL GotoXY
    MOV EAX, totalScore         ;
    INC EAX
    CALL WriteDec
    MOV totalScore, EAX         ; 

    POP EDX                 ; 

    foodNotGobbled:         ; 
    CALL GotoXY             ;
    MOV EAX, lightRed + (lightRed * 16)
    CALL setTextColor       ; 
    MOV AL, ' '             ; 
    CALL WriteChar
    MOV DH, 24              ; 
    MOV DL, 79
    CALL GotoXY

    RET                     ;

MoveSnake ENDP

createFood PROC USES EAX EBX EDX



    redo:                       
    MOV EAX, 24                 
    CALL RandomRange            
    MOV DH, AL

    MOV EAX, 80                 
    CALL RandomRange            
    MOV DL, AL

    CALL accessIndex            

    CMP BX, 0                   
    JNE redo                    

    MOV foodR, DH                  
    MOV foodC, DL                  

    MOV EAX, white + (yellow * 16)
    CALL setTextColor
    CALL GotoXY                 
    MOV AL, ' '                 
    CALL WriteChar

    RET

createFood ENDP

accessIndex PROC USES EAX ESI EDX



    MOV BL, DH      
    MOV AL, 80      
    MUL BL          
    PUSH DX         
    MOV DH, 0       
    ADD AX, DX      
    POP DX          
    MOV ESI, 0      
    MOV SI, AX      
    SHL SI, 1       

    MOV BX, a[SI]   

    RET

accessIndex ENDP

saveIndex PROC USES EAX ESI EDX


    PUSH EBX        
    MOV BL, DH      
    MOV AL, 80      
    MUL BL          
    PUSH DX         
    MOV DH, 0       
    ADD AX, DX      
    POP DX          
    MOV ESI, 0     
    MOV SI, AX      
    POP EBX         
    SHL SI, 1       
                    
    MOV a[SI], BX   

    RET

saveIndex ENDP

Paint PROC USES EAX EDX EBX ESI


    MOV EAX, blue + (white * 16)   
    CALL SetTextColor

    MOV DH, 0                       

    loop1:                          
        CMP DH, 24                  
        JGE endLoop1                

        MOV DL, 0                   

        loop2:                      
            CMP DL, 80              
            JGE endLoop2            
            CALL GOTOXY             

            MOV BL, DH              
            MOV AL, 80              
            MUL BL
            PUSH DX                 
            MOV DH, 0               
            ADD AX, DX              
            POP DX                  
            MOV ESI, 0              
            MOV SI, AX              
            SHL SI, 1              
                                    
                                    
            MOV BX, a[SI]           

            CMP BX, 0               
            JE NoPrint              

            CMP BX, 0FFFFh          
            JE printHurdle          

            MOV AL, ' '             
            CALL WriteChar          
            JMP noPrint             

            PrintHurdle:            
            MOV EAX, blue + (gray * 16) 
            CALL SetTextColor

            MOV AL, ' '             
            CALL WriteChar

            MOV EAX, blue + (white * 16)    
            CALL SetTextColor               

            NoPrint:
            INC DL                 
            JMP loop2               

    endLoop2:                       
        INC DH                      
        JMP loop1                   

endLoop1:                           

RET

Paint ENDP

GenLevel PROC



    CMP AL, 1               
    JNE nextL               

    RET                     

    nextL:                  
    CMP AL, 2
    JNE nextL2             

    MOV DH, 8               
    MOV BX, 0FFFFh          

    rLoop:                  
        CMP DH, 13          
        JE endRLoop         

        MOV DL, 10           
        CALL saveIndex      
        MOV DL, 20          
        CALL saveIndex      
        INC DH              
        JMP rLoop           
    endRLoop:

    MOV DL, 10               

    cLoop:                  
        CMP DL, 21          
        JE endCLoop         

        MOV DH, 8           
        CALL saveIndex      
        
        INC DL              
        JMP cLoop           

        endCLoop:

    MOV DH, 10               

    rLoop1:                  
        CMP DH, 13          
        JE endRLoop1        

        MOV DL, 25           
        CALL saveIndex      
        MOV DL, 35          
        CALL saveIndex      
        INC DH              
        JMP rLoop1          
    endRLoop1:

    MOV DL, 25               

    cLoop1:                  
        CMP DL, 36          
        JE endCLoop1         

        MOV DH, 10           
        CALL saveIndex     
        
        INC DL              
        JMP cLoop1          

        endCLoop1:
    MOV DH, 8            

    rLoop2:                  
        CMP DH, 13          
        JE endRLoop2        

        MOV DL, 40           
        CALL saveIndex      
        MOV DL, 50          ;
        CALL saveIndex      ;
        INC DH              ;
        JMP rLoop2           ;
    endRLoop2:

    MOV DL, 40              

    cLoop2:                 
        CMP DL, 51          
        JE endCLoop2        

        MOV DH, 8           
        CALL saveIndex      
        
        INC DL              
        JMP cLoop2           

        endCLoop2:
    
     MOV DH, 17               
    rLoop3:                  
        CMP DH, 23          
        JE endRLoop3         ;

        MOV DL, 50           
        CALL saveIndex      
        MOV DL, 60          
        CALL saveIndex      
        INC DH              ;
        JMP rLoop3         
    endRLoop3:

    MOV DL, 50               

    cLoop3:                  
        CMP DL, 61          
        JE endCLoop3        

        MOV DH, 22          
        CALL saveIndex      
        
        INC DL              
        JMP cLoop3          

        endCLoop3:

    RET

    nextL2:                

        MOV newDirection, 'd'      
        MOV DH, 3          
        MOV DL, 0           
        MOV BX, 0FFFFh      

    rLoop11:                  
        CMP DH, 11         
        JE endRLoop11        

        MOV DL, 10           
        CALL saveIndex      
        
        INC DH              
        JMP rLoop11          
    endRLoop11:

    MOV DL, 10              

    cLoop11:                  
        CMP DL, 19          
        JE endCLoop11         

        MOV DH, 3           
        CALL saveIndex      
        
	MOV DH, 10           
        CALL saveIndex      
        INC DL              
        JMP cLoop11           

        endCLoop11:

    MOV DH, 15 

    rLoop12:                  
        CMP DH, 23          
        JE endRLoop12         

        MOV DL, 10           
        CALL saveIndex      
        
        INC DH              
        JMP rLoop12           
    endRLoop12:

    MOV DL, 10               

    cLoop12:                  
        CMP DL, 19          
        JE endCLoop12         

        MOV DH, 15           
        CALL saveIndex      
        
	MOV DH, 22           
        CALL saveIndex      
        INC DL              
        JMP cLoop12           

        endCLoop12:

    MOV DL, 18              

    cLoop13:                  
        CMP DL, 65          
        JE endCLoop13         

        MOV DH, 7         
        CALL saveIndex      
        
	MOV DH, 18           
        CALL saveIndex      
        INC DL              
        JMP cLoop13           

        endCLoop13:

    MOV DH, 5 

    rLoop14:                  
        CMP DH, 21          
        JE endRLoop14         

        MOV DL, 70           
        CALL saveIndex      
        
        INC DH              
        JMP rLoop14           
    endRLoop14:

    MOV DL, 63               
    cLoop14:                  
        CMP DL, 71          
        JE endCLoop14         

        MOV DH, 5           
        CALL saveIndex      
        
	MOV DH, 20           
        CALL saveIndex      
        INC DL             
        JMP cLoop14           

        endCLoop14:
    RET

GenLevel ENDP

END main
