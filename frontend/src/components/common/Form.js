import React, { useContext, useState, useEffect } from 'react';

const FormContext = React.createContext();

export const FormInput = props => {
    const {handleInputChange} = useContext(FormContext);
    const {getError} = useContext(FormContext);
    const {onInputBlur} = useContext(FormContext);

    const [value, setValue] = useState(props.value);

    useEffect( () => {
        if (value != undefined)
        handleInputChange(props.name, value);
    }, [value]);

    const onChange = (e) => {
        setValue(e.target.value);
    }

    const errorElement = (
        <div className="ui mini error message">
            {getError(props.name)}
        </div>
    );

    if (props.textarea){
        return (
            <div className={`field ${getError(props.name) !== null ? 'error' : ''}`}>
                <textarea 
                onChange={onChange}
                onBlur={() => onInputBlur(props.name)} {...props}
                value={value} />
                {getError(props.name) !== null ? errorElement : null}
            </div>
        );
    }

    return (
        <div className={`field ${getError(props.name) !== null ? 'error' : ''}`}>
            <input 
            onChange={onChange}
            onBlur={() => onInputBlur(props.name)} {...props}
            value={value} />
            {getError(props.name) !== null ? errorElement : null}
        </div>
    );

}

export class Form extends React.Component {

    state = {
        form: {},
        blurred: {},
        errors: {}
    }

    componentDidMount() {
        const stateForm = {};
        const stateBlur = {};
        this.props.children.forEach(c => {
            if (c.type.name === 'FormInput'){
                const name = c.props.name;
                stateForm[name] = '';
                stateBlur[name] = false;
            }
        });

        this.setState({
            form: stateForm,
            blurred: stateBlur
        })
        console.log("comp did mount");
    }

    onInputBlur = (name) => {
        this.setState(prevState => ({
            blurred: {                
                ...prevState.blurred,  
                [name]: true  
            }
        }));
    }

    getError = (name) => {
        if (this.state.errors[name] !== undefined && this.state.blurred[name]){
            return this.state.errors[name];
        }
        return null;
    }

    handleInputChange = (name, value) => {
        this.setState(prevState => ({
            form: {
                ...prevState.form,
                [name]: value
            }
        }), () => {
            this.setState({
                errors: this.props.validate(this.state.form)
            });
        });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.validate(this.state.form);
        if (Object.keys(this.state.errors).length === 0) {
            this.props.onSubmit(this.state.form);
        }        
    };

    render () {

        return (
            <FormContext.Provider value= {{
                handleInputChange: this.handleInputChange,
                getError: this.getError,
                onInputBlur: this.onInputBlur}}>
                <form className={`${this.props.size} ui form ${Object.getOwnPropertyNames(this.state.errors).length !== 0 ? 'error' : ''}`} onSubmit={(e) => this.handleSubmit(e)} >
                    {this.props.children}
                </form>
            </FormContext.Provider>
        );
    }
}