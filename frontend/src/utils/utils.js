
export const randomColor = () => {
    const colors = [
        'red',
        'orange',
        'olive',
        'green',
        'teal',
        'blue',
        'violet',
        'purple',
        'pink'
    ];

    return colors[Math.floor(Math.random() * (colors.length))];
}
