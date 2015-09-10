; noripi's code
(defun euclid (x y) 
  (let (modulo smaller larger)
    (setq larger (max x y))
    (setq smaller (min x y))
    (setq modulo (mod larger smaller))
    (if (= modulo 0)
      smaller
      (euclid smaller modulo)
    )
  )
)
(defun solve (ax ay bx by) 
  (let (xdiff ydiff)
    (setq xdiff (abs (- ax bx)))
    (setq ydiff (abs (- ay by)))
    (if (and (= xdiff 0) (= ydiff 0)) 
      0
      (if (= xdiff 0)        
        (- ydiff 1)
        (if (= ydiff 0)
          (- xdiff 1)
          (- (euclid xdiff ydiff) 1)  
        )
      )  
    )
  )
)


; test code
(defun test_euclid (ax ay bx by answer) 
  (if (= (solve ax ay bx by) answer) 
    'PASS 
    'FAIL
  )
)

(print (test_euclid 1 11 5 3 3))
(print (test_euclid 0 0 1 3 0))
(print (test_euclid 3 8 3000 8000 998))
(print (test_euclid 5 3 -3 -1 3))
(print (test_euclid 0 0 6486480 4194304 15))
(print (test_euclid 6486480 4194304 6486480 4194304 0))
(print (test_euclid 0 0 0 2 1))
