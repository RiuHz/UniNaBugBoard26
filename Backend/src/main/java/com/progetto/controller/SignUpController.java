

@RestController
@RequestMapping("/api/sign-up")
public class SignUpController{

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public void createUser(@RequestBody SignUpRequest utente) {
        signUpService.creaUtente(utente);
    }

}