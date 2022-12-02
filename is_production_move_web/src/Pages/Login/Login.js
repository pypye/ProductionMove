import { Form } from "../../components/NestedComponents";
import { Button } from "../../components/PrimaryComponents";

export default function Login() {
    const validUsername = (input) => {
        if (input.length === 0) {
            return { state: false, content: 'Username is required' };
        } else if (input.length < 6) {
            return { state: false, content: 'Username must be at least 6 characters long' };
        }
        return { state: true };
    }
    return (
        <Form width='30rem'>
            <h1>Login</h1>
            <Form.Input label="Username" type="text" validation={validUsername} />
            <Form.Input label="Password" type="password" validation={validUsername} />
            <Button>Submit</Button>
        </Form>
    )
}